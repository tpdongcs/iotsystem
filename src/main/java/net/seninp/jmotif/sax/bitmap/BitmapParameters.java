package net.seninp.jmotif.sax.bitmap;

import com.beust.jcommander.Parameter;
import net.seninp.jmotif.sax.NumerosityReductionStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Parameters accepted by the bitmap printer and their default values.
 * 
 * @author psenin
 * 
 */
public class BitmapParameters {

  // general setup
  //
  @Parameter
  public List<String> parameters = new ArrayList<String>();

  @Parameter(names = "--verbose", description = "Level of verbosity")
  public Integer verbose = 1;

  @Parameter(names = { "--help", "-h" }, help = true)
  public boolean help;

  // dataset
  //
  @Parameter(names = { "--data_in", "-d" }, description = "The input file name")
  public static String IN_FILE;

  // output
  //
  @Parameter(names = { "--data_out", "-o" }, description = "The output file name")
  public static String OUT_FILE = "shingling_out.txt";

  @Parameter(names = { "--bitmap_out", "-b" }, description = "The bitmap output file name")
  public static String BITMAP_FILE = "my-chart.png";

  // discretization parameters
  //
  @Parameter(names = { "--window_size", "-w" }, description = "Sliding window size")
  public static int SAX_WINDOW_SIZE = 30;

  @Parameter(names = { "--word_size", "-p" }, description = "PAA word size")
  public static int SAX_PAA_SIZE = 6;

  @Parameter(names = { "--alphabet_size", "-a" }, description = "SAX alphabet size")
  public static int SAX_ALPHABET_SIZE = 4;

  @Parameter(names = "--strategy", description = "Numerosity reduction strategy")
  public static NumerosityReductionStrategy SAX_NR_STRATEGY = NumerosityReductionStrategy.NONE;

  @Parameter(names = "--threshold", description = "Normalization threshold")
  public static double SAX_NORM_THRESHOLD = 0.01;

  // shingling parameter
  //
  @Parameter(names = { "--shingle_size",
      "-s" }, description = "Shingle size (i.e. Level in terms of the original article)")
  public static int SHINGLE_SIZE = 2;

}
