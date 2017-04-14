package net.physionet;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhysionetData {
	public String getAge() {
		return age;
	}

	public Gender getSex() {
		return sex;
	}

	public Date getEcgDate() {
		return ecgDate;
	}

	public String getReason() {
		return reason;
	}

	public String getAcuteInfarction() {
		return acuteInfarction;
	}

	public boolean isFormerInfarction() {
		return formerInfarction;
	}

	public String getAdditionalDiagnoses() {
		return additionalDiagnoses;
	}

	public int getIsSmoker() {
		return isSmoker;
	}

	public Date getInfarctionDate() {
		return infarctionDate;
	}

	private String age;
	private Gender sex;
	private Date ecgDate;
	private String reason;
	private String acuteInfarction;
	private boolean formerInfarction;
	private String additionalDiagnoses =  null;
	private int isSmoker = 0;
	private Date infarctionDate;
	private int sps = 1000;
	public double[] getEcgDataD1() {
		return ecgDataD1;
	}

	public void setEcgDataD1(double[] ecgDataD1) {
		this.ecgDataD1 = ecgDataD1;
	}

	public double[] getEcgDataD2() {
		return ecgDataD2;
	}

	public void setEcgDataD2(double[] ecgDataD2) {
		this.ecgDataD2 = ecgDataD2;
	}

	public double[] getEcgDataD3() {
		return ecgDataD3;
	}

	public void setEcgDataD3(double[] ecgDataD3) {
		this.ecgDataD3 = ecgDataD3;
	}

	private double[] ecgDataD1;
	private double[] ecgDataD2;
	private double[] ecgDataD3;

	
	public PhysionetData(File headerFile){
		getInformationFromFile(headerFile);
	}

	private Matcher GetMatcher(String regex, String content){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		return matcher;
	}
	
	private String getString(String data,String type){
		String regEx = "# "+type+": (.*)";
		Matcher matcher = GetMatcher(regEx, data);
		if(matcher.find()){
			return matcher.group(1);
		}
		else{
			return "";
		}
	}
	private Gender getSex(String data) {
		String regExSex = "# sex: (.*)";
		Matcher matcherSex = GetMatcher(regExSex, data);
		if(matcherSex.find()){
			String gender = matcherSex.group(1);
			if(gender == "male")
				return Gender.Male;
			if(gender == "female")
				return Gender.Female;
		}
		return Gender.Male;
	}
	
	private Date getDate(String data,String type) {
		String regExEcgDate = "# "+type+": (.*)";
		Matcher matcherECGDate = GetMatcher(regExEcgDate, data);
		if(matcherECGDate.find()){
			String dateString = matcherECGDate.group(1);
			DateFormat df;
			if (type.equals("ECG date")){
				df = new SimpleDateFormat("dd/MM/yyyy");
			}
			else {
				df = new SimpleDateFormat("dd-MMM-yy");
			}

			try {
				return (Date)df.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		return null;
	}
	
	private void getInformationFromFile(File headerFile) {
		String data = null;
		try {
			data = FileUtils.readFileToString(headerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//get Age
			age = getString(data,"age");
			reason = getString(data,"Reason for admission");
			acuteInfarction = getString(data, "Acute infarction (localization)");
			formerInfarction = ("no".equals(getString(data, "Former infarction (localization)")))?false:true;
			additionalDiagnoses = getString(data,"Additional diagnoses");
			isSmoker = ("no".equals(getString(data,"Diabetes mellitus")))?-1:(("yes".equals(getString(data,"Diabetes mellitus")))?1:0);

			//get sex
			sex = getSex(data);
			//get ecg date
			ecgDate = getDate(data,"ECG date");
			infarctionDate = getDate(data,"Infarction date (acute)");
			//System.out.println(ecgDate);
		}
	}
}
