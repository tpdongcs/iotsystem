package net.physionet;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExportCSV {
	private File _file;
	private List<Integer> i = new ArrayList<Integer>();
	private List<Integer> ii = new ArrayList<Integer>();
	private List<Integer> iii = new ArrayList<Integer>();
	public ExportCSV(File file){
		_file = file;
	}
	private void readfile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(_file.getAbsolutePath()));
		try {
		    String line;
		    while((line = br.readLine()) != null){
			    String[] input = line.split("\t");
			    i.add(Integer.parseInt(input[1].trim()));
			    ii.add(Integer.parseInt(input[2].trim()));
			    iii.add(Integer.parseInt(input[3].trim()));
		    }
		} finally {
		    br.close();
		}
	}
	public void export() throws IOException{
		readfile();
		String patientFolder = _file.getParentFile().getAbsolutePath();
		PrintWriter icsv = new PrintWriter(patientFolder+"\\"+ FilenameUtils.removeExtension(_file.getName())+"_i.csv");
		PrintWriter iicsv = new PrintWriter(patientFolder+"\\"+FilenameUtils.removeExtension(_file.getName())+"_ii.csv");
		PrintWriter iiicsv = new PrintWriter(patientFolder+"\\"+FilenameUtils.removeExtension(_file.getName())+"_iii.csv");
		int size = i.size();
		for(int j = 0 ; j< size; j++){
			icsv.println(i.get(j));
			iicsv.println(ii.get(j));
			iiicsv.println(iii.get(j));
		}
		icsv.close();
		iicsv.close();
		iiicsv.close();
	}
}
