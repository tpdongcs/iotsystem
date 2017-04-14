package org.jfree;

import org.apache.commons.io.FilenameUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TS2Chart {
    public static void TS2ChartFile(double[] array,String fileName)throws Exception{
        final TimeSeries series = new TimeSeries("Random Data");
        Second current = new Second();
        for (int i =0;i <array.length;i++){
            series.add(current,array[i]);
            current = (Second) current.next();
        }
        final XYDataset dataset = (XYDataset) new TimeSeriesCollection(series);
        JFreeChart timechart = ChartFactory.createTimeSeriesChart(
                "Computing Test",
                "Seconds",
                "Value",
                dataset,
                false,
                false,
                false);

        int width = 3000; /* Width of the image */
        int height = 400; /* Height of the image */
        File timeChart = new File(
                "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Projects\\SAX-master\\ptdb\\"+fileName+".jpeg");
        System.out.println("Converted ");
        ChartUtilities.saveChartAsJPEG(timeChart, timechart, width, height);

    }
    public static void compare(double[] array, Object[] motifs)throws Exception{
        final TimeSeries series = new TimeSeries("Random Data");
        Second current = new Second();
        for (int i =0;i <array.length;i++){
            if (Arrays.asList(motifs).contains(i)){
                series.add(current,-2000);
                current = (Second) current.next();
                continue;
            }
            series.add(current,array[i]);
            current = (Second) current.next();
        }
        final XYDataset dataset = (XYDataset) new TimeSeriesCollection(series);
        JFreeChart timechart = ChartFactory.createTimeSeriesChart(
                "Computing Test",
                "Seconds",
                "Value",
                dataset,
                false,
                false,
                false);

        int width = 3000; /* Width of the image */
        int height = 400; /* Height of the image */
        File timeChart = new File(
                "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\compare.jpeg");
        System.out.println("Converted ");
        ChartUtilities.saveChartAsJPEG(timeChart, timechart, width, height);

    }
    public static void charArray2ChartFile(char[] array) throws Exception{
        final TimeSeries series = new TimeSeries("Random Data");
        Second current = new Second();

        for (int i =0;i <array.length;i++){
            series.add(current,(int)array[i] - 97);
            current = (Second) current.next();
        }
        final XYDataset dataset = (XYDataset) new TimeSeriesCollection(series);
        JFreeChart timechart = ChartFactory.createTimeSeriesChart(
                "Computing Test",
                "Seconds",
                "Value",
                dataset,
                false,
                false,
                false);

        int width = 800; /* Width of the image */
        int height = 400; /* Height of the image */
        File timeChart = new File(
                "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\char_chart.jpeg");
        System.out.println("Converted ");
        ChartUtilities.saveChartAsJPEG(timeChart, timechart, width, height);
    }
    public static void symbolArray2ChartFile(String input) throws Exception{
        char[] array = input.split(" -> ")[0].toCharArray();
        final TimeSeries series = new TimeSeries("Random Data");
        Second current = new Second();

        for (int i =0;i <array.length;i++){
            series.add(current,(int)array[i] - 97);
            current = (Second) current.next();
        }
        final XYDataset dataset = (XYDataset) new TimeSeriesCollection(series);
        JFreeChart timechart = ChartFactory.createTimeSeriesChart(
                "Computing Test",
                "Seconds",
                "Value",
                dataset,
                false,
                false,
                false);

        int width = 2000; /* Width of the image */
        int height = 400; /* Height of the image */
        File timeChart = new File(
                "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\symbol_chart.jpeg");
        System.out.println("Converted ");
        ChartUtilities.saveChartAsJPEG(timeChart, timechart, width, height);
    }
    public static void TSFile2ChartFile(File file) throws Exception{
        int interval = 0;
        String line ="";
        BufferedReader br = new BufferedReader(new FileReader(file));
        final TimeSeries series = new TimeSeries("Random Data");
        Second current = new Second();
        int i = 0;
        while ((line = br.readLine()) != null){
            if (i++ <= interval) {
                continue;
            }
            i = 0;
            series.add(current,Double.parseDouble(line));
            current = (Second) current.next();
        }
        final XYDataset dataset = (XYDataset) new TimeSeriesCollection(series);
        JFreeChart timechart = ChartFactory.createTimeSeriesChart(
                "Computing Test",
                "Seconds",
                "Value",
                dataset,
                false,
                false,
                false);

        int width = 3000; /* Width of the image */
        int height = 400; /* Height of the image */
        File timeChart = new File(file.getParentFile().getAbsolutePath()+"\\"+ FilenameUtils.removeExtension(file.getName())+"_chart.jpeg");
        System.out.println("Converted from "+file.getParentFile().getName()+" to jpeg file "+file.getName());
        ChartUtilities.saveChartAsJPEG(timeChart, timechart, width, height);
    }
    /*public static void main(final String[] args) throws Exception {
        for (int i = 1; i <= 294; i++) {
            try {
                String patientFolderPath = "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient" + ((i < 10) ? ("00" + i) : ((i < 100) ? ("0" + i) : (i)));
                File folder = new File(patientFolderPath);
                File[] listOfFiles = folder.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith("csv");
                    }
                });
                for (File file : listOfFiles) {
                    TSFile2ChartFile(file);
                }
            } catch (IOException e) {
                System.out.println("Cannot read file");
                continue;
            } catch (SeriesException e ) {
                System.out.println( "Error adding to series" );
                continue;
            } catch (Exception e) {
                continue;
            }
        }
    }*/
    public static void main(final String[] args) throws Exception {
        try {
            //String patientFolderPath = "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\SAX-master\\src\\resources\\test-data";
            //File folder = new File(patientFolderPath);
            TSFile2ChartFile(new File("C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient003\\s0017lre_i.csv"));
        } catch (IOException e) {
            System.out.println("Cannot read file");
        } catch (SeriesException e ) {
            System.out.println( "Error adding to series" );
        } catch (Exception e) {
        }
    }
}