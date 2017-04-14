package net.physionet;

import net.seninp.jmotif.sax.NumerosityReductionStrategy;
import net.seninp.jmotif.sax.TSProcessor;
import net.seninp.jmotif.sax.discord.DiscordRecords;
import net.seninp.jmotif.sax.discord.HOTSAXImplementation;

/**
 * Created by bonamana2811 on 4/13/2017.
 */
public class AnomalyECG
{

    public static void main(final String[] args) throws Exception {
        DiscordRecords dr = null;
        int windowSize = 800;
        int ppaSize = 10;
        float nThreashot = 0.01f;
        int cutSize = 6;
        String anomalyFolder = "C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb";
        double[] series = TSProcessor.readFileColumn("C:\\Users\\bonamana2811\\Desktop\\Projects\\SAX-master\\ptdb\\patient003\\s0017lre_i.csv",0,0);
        ECGPreProcessing.ReduceNoiseViaDoubleMedianFilter(series,200,600);
        dr = HOTSAXImplementation.series2Discords(series,4,windowSize,ppaSize,cutSize, NumerosityReductionStrategy.MINDIST,nThreashot);
    }
}
