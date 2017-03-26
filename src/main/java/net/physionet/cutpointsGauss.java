package net.physionet;
import org.apache.commons.math3.distribution.*;

public class cutpointsGauss {
	public static double[] findCutPointGauss(int num) {
		double[] result = new double[num-1];
		NormalDistribution nd = new NormalDistribution();
		double part = (100.00/(double) num)/100;
		for(int i=0;i<num-1;i++){
			result[i] = nd.inverseCumulativeProbability(part*(i+1));
		}
		return result;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 10;
		double[] result=findCutPointGauss(num);
		for(int i=0; i<result.length; i++){
			System.out.println(result[i]);
		}
	}

}
