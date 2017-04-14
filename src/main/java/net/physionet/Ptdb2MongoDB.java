package net.physionet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcmut.edu.repository.PhysionetDataRepository;
import net.seninp.jmotif.sax.TSProcessor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by bonamana2811 on 4/3/2017.
 */
public class Ptdb2MongoDB {
    public static void main(String[] args) throws Exception {
        String resultFilePath = "C:\\jsonFile.json";
        File resultFile = new File(resultFilePath);
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 294; i++) { //294
            if (i%20 == 0){
                System.out.println("Writing to file......................................");
                Files.write(Paths.get("C:\\jsonFile.json"),sb.toString().getBytes(), StandardOpenOption.APPEND);
                sb = new StringBuilder();
            }
            try {
                System.out.println("Insert Patient " + i);
                String patientFolderPath = "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient" + ((i < 10) ? ("00" + i) : ((i < 100) ? ("0" + i) : (i)));
                File folder = new File(patientFolderPath);
                File[] listOfFiles = folder.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith("hea");
                    }
                });
                for (File headerFile : listOfFiles) {
                    System.out.println(" - Insert record " + headerFile.getName() + " to database");
                    String csvFilePath = headerFile.getAbsolutePath().split("\\.")[0];
                    double[] seriesD1 = TSProcessor.readFileColumn(csvFilePath + "_i.csv", 0, 0);
                    double[] seriesD2 = TSProcessor.readFileColumn(csvFilePath + "_ii.csv", 0, 0);
                    double[] seriesD3 = TSProcessor.readFileColumn(csvFilePath + "_iii.csv", 0, 0);
                    PhysionetData data = new PhysionetData(headerFile);
                    data.setEcgDataD1(seriesD1);
                    data.setEcgDataD2(seriesD2);
                    data.setEcgDataD3(seriesD3);
                    String jsonString = mapper.writeValueAsString(data);
                    sb.append(jsonString + "\n");
                    //System.out.println(jsonString);
                }

            } catch (Exception e) {
                continue;
            }
        }
    }
}
