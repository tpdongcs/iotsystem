package net.physionet;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import net.seninp.jmotif.sax.NumerosityReductionStrategy;
import net.seninp.jmotif.sax.SAXProcessor;
import net.seninp.jmotif.sax.TSProcessor;
import net.seninp.jmotif.sax.alphabet.Alphabet;
import net.seninp.jmotif.sax.alphabet.NormalAlphabet;
import net.seninp.jmotif.sax.datastructure.SAXRecord;
import net.seninp.jmotif.sax.datastructure.SAXRecords;
import org.jfree.TS2Chart;

import java.util.*;

/**
 * Created by bonamana2811 on 3/27/2017.
 */
public class FindMotifLength {
    public static void main(final String[] args) throws Exception {
        int windowSize = 800;
        int ppaSize = 50;
        float nThreashot = 0.01f;
        int cutSize = 7;
        double[] series = TSProcessor.readFileColumn("C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient003\\s0017lre_i.csv",0,0);
        SAXProcessor sp = new SAXProcessor();
        TSProcessor tp = new TSProcessor();
        Alphabet na = new NormalAlphabet();
        //SAXRecords sample = sp.ts2saxViaWindow(series,windowSize,ppaSize,na.getCuts(cutSize),NumerosityReductionStrategy.NONE,nThreashot);
        //String a = sample.getSAXString(",");
        ECGPreProcessing.ReduceNoiseViaDoubleMedianFilter(series,200,600);
        TS2Chart.TS2ChartFile(series,"median");

        char[] sample = sp.ts2string(series,series.length/ppaSize,na.getCuts(cutSize),nThreashot);
        StringBuilder sb = new StringBuilder();
        for (int i =0; i <2000;i++){
            sb.append(sample[i]);
        }
        TS2Chart.symbolArray2ChartFile(sb.toString());
        System.out.println(sb.toString());

        /*SAXRecords sample = sp.ts2saxByChunking(series,series.length/40,na.getCuts(cutSize),nThreashot);
        List<Integer> QRSIndexes = new ArrayList<Integer>(sample.getRecord(cutSize - 1).getIndexes());
                //.getIndexes();
        Collections.sort(QRSIndexes, (Comparator<Integer>) (o1, o2) -> Integer.compare(o1,o2));
        int count = 1;
        int avrCircuit = (QRSIndexes.get(1) - QRSIndexes.get(0));
        for (int i =1; i < QRSIndexes.size() - 1;i++){
            int newAvr = (QRSIndexes.get(i + 1) - QRSIndexes.get(i));
            avrCircuit = avrCircuit*count/(count + 1) + newAvr/(count + 1);
            count++;
        }
        System.out.println(avrCircuit);
*/

        //Map<String, Integer> sample = sp.ts2Shingles(series,windowSize,ppaSize,cutSize,NumerosityReductionStrategy.EXACT,nThreashot,10);
        // sp.ts2saxViaWindowGlobalZNorm(series,1200,)
        /*char[] symbols = sp.ts2string(series,series.length/10,na.getCuts(20),nThreashot);



        long motifLength = 0;
        int count = 0;
        *//*for (char a:symbols){
            if
        }*//*
        TS2Chart.symbolArray2ChartFile(String.valueOf(symbols));*/
    }
}
