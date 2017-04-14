package net.physionet;

import java.util.Arrays;

/**
 * Created by bonamana2811 on 3/30/2017.
 */
public class ECGPreProcessing {
    private static int partition(double arr[], int left, int right)
    {
        int i = left, j = right;
        double tmp;
        double pivot = arr[left];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };

        return i;
    }

    private static int findMedian(double arr[], int left, int right) {
        if (left == right) return left;
        int medianIndex = arr.length/2;
        int index = partition(arr, left, right);
        if (left <= medianIndex && medianIndex <= index - 1 )
            return findMedian(arr, left, index - 1);
        else{
            return findMedian(arr, index, right);
        }

    }
    public static double median(double[] series, int index, int medianRange){
        int from = (index - medianRange/2 <= 0) ? 0 : index - medianRange/2;
        int to = (index + medianRange/2 >= series.length - 1) ? series.length - 1 : index + medianRange/2;
        double[] cloneSeries = Arrays.copyOfRange(series,from,to + 1);
        int medianIndex = findMedian(cloneSeries,0,cloneSeries.length - 1);
        return new Double(cloneSeries[medianIndex]);
    }
    public static void ReduceNoiseViaMedianFilter(double[] series,int medianRange){
        for (int i =0;i<series.length;i++){
            series[i] = series[i] - median(series,i,medianRange);
        }
    }
    public static void ReduceNoiseViaDoubleMedianFilter(double[] series,int medianRangePQRS, int medianRangeT){
        double[] baseline = new double[series.length];
        for (int i =0;i<series.length;i++){
            baseline[i] = median(series,i,medianRangePQRS);
        }
        for (int i = 0; i <series.length;i++){
            series[i] = series[i] - median(baseline,i,medianRangeT);
        }
    }
}
