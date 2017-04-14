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
 * Created by bonamana2811 on 4/14/2017.
 */
public class BestMotif {
    public static void main(final String[] args) throws Exception {
        int windowSize = 800;
        int ppaSize = 10;
        float nThreashot = 0.01f;
        int cutSize = 6;
        double[] series = TSProcessor.readFileColumn("C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient003\\s0017lre_i.csv",0,0);
        ECGPreProcessing.ReduceNoiseViaDoubleMedianFilter(series,200,600);
        SAXProcessor sp = new SAXProcessor();
        TSProcessor tp = new TSProcessor();
        Alphabet na = new NormalAlphabet();
        // sp.ts2saxViaWindowGlobalZNorm(series,1200,)
        double[] motiftime = new double[90];
        for (int i = windowSize/2; i < windowSize*1.5;i+=10){
            SAXRecords saxData = sp.ts2saxViaWindow(series, i,ppaSize, na.getCuts(cutSize), NumerosityReductionStrategy.MINDIST,nThreashot);
            ArrayList<SAXRecord> motifs = saxData.getMotifs(1);
            motiftime[(i - windowSize/2)/10] = motifs.get(0).getIndexes().size();
            System.out.println(i);
            //int index = (int)motifs.get(0).getIndexes().toArray()[0];
            //SAXRecord c = saxData.getByIndex(index);
            //System.out.println(c.toString());
        }
        TS2Chart.TS2ChartFile(motiftime,"motif_count");
    }
}
