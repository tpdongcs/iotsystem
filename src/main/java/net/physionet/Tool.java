package net.physionet;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class Tool {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        for (int i = 1; i <= 294; i++) {
            try {
                String patientFolderPath = "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient" + ((i < 10) ? ("00" + i) : ((i < 100) ? ("0" + i) : (i)));
                File folder = new File(patientFolderPath);
                File[] listOfFiles = folder.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith("txt");
                    }
                });
                for (File file : listOfFiles) {
                    ExportCSV expCSV = new ExportCSV(file);
                    expCSV.export();
                    System.out.println("converted file patient " + i + " file " + file.getName());
                }
            } catch (Exception e) {
                continue;
            }
        }
    }
}
