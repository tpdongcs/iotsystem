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

public class phyosinet {
	private String pathFile;
	public int age;
	public Gender sex;
	public Date ecgDate;
	public String reason;
	public String acuteInfarction;
	public boolean formerInfarction;
	public String additionalDiagnoses =  null;
	public ExtendedBoolean Smoker = ExtendedBoolean.Unknow;
	public Date infarctionDate;
	
	public phyosinet(String path){
		pathFile = path;
		getInformationFromFile();
	}
	private Matcher GetMatcher(String regex, String content){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		return matcher;
	}
	
	private int getAge(String data){
		String regExAge = "# age: (.*)";
		Matcher matcherAge = GetMatcher(regExAge, data);
		if(matcherAge.find()){
			try {
				return Integer.parseInt(matcherAge.group(1));
			} catch (Exception e) {
				return -1;
			}
		}
		else{
			return -1;
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
	
	private Date getEcgDate(String data) {
		String regExEcgDate = "# ECG date: (.*)";
		Matcher matcherECGDate = GetMatcher(regExEcgDate, data);
		if(matcherECGDate.find()){
			String dateString = matcherECGDate.group(1);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				return (Date)df.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		return null;
	}
	
	private void getInformationFromFile() {
		String data = null;
		try {
			data = FileUtils.readFileToString(new File(pathFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//get Age
			age = getAge(data);
			//get sex
			sex = getSex(data);
			//get ecg date
			ecgDate = getEcgDate(data);
			System.out.println(ecgDate);
		}
	}
}
