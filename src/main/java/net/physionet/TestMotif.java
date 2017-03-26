package net.physionet;

import net.seninp.jmotif.sax.NumerosityReductionStrategy;
import net.seninp.jmotif.sax.SAXProcessor;
import net.seninp.jmotif.sax.TSProcessor;
import net.seninp.jmotif.sax.alphabet.Alphabet;
import net.seninp.jmotif.sax.alphabet.NormalAlphabet;
import net.seninp.jmotif.sax.datastructure.SAXRecord;
import net.seninp.jmotif.sax.datastructure.SAXRecords;
import org.jfree.TS2Chart;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by bonamana2811 on 3/9/2017.
 */
public class TestMotif {
    public static void main(final String[] args) throws Exception {
        int windowSize = 800;
        int ppaSize = 10;
        float nThreashot = 0.01f;
        int cutSize = 6;
        double[] series = TSProcessor.readFileColumn("C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient003\\s0017lre_i.csv",0,0);
        SAXProcessor sp = new SAXProcessor();
        TSProcessor tp = new TSProcessor();
        Alphabet na = new NormalAlphabet();
       // sp.ts2saxViaWindowGlobalZNorm(series,1200,)
        SAXRecords saxData = sp.ts2saxViaWindow(series, windowSize,ppaSize, na.getCuts(cutSize), NumerosityReductionStrategy.MINDIST,nThreashot);
        //SAXRecords saxData = sp.ts2saxByChunking(series,series.length/ppaSize,na.getCuts(cutSize),nThreashot);
        //SAXRecords saxData = sp.ts2saxViaWindowGlobalZNorm(series,800,50,na.getCuts(10),NumerosityReductionStrategy.MINDIST,0.01f);
        System.out.println(saxData.getSAXString(" "));
        System.out.println("-----------");
        ArrayList<SAXRecord> motifs = saxData.getMotifs(10);
        System.out.println(motifs.get(0).getIndexes().size());
        int index = (int)motifs.get(0).getIndexes().toArray()[0];
        SAXRecord c = saxData.getByIndex(index);
        System.out.println(c.toString());
        //TS2Chart.TS2ChartFile(tp.znorm(series,nThreashot));
        TS2Chart.TS2ChartFile(Arrays.copyOfRange(series,index,index + windowSize));
        Object[] motifA = motifs.get(0).getIndexes().toArray();
        TS2Chart.compare(series,motifA);
    }
}
