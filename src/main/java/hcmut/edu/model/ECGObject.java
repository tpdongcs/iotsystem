package hcmut.edu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by HuyNho on 4/4/2017.
 */

public class ECGObject {

    private double[] ECGarr;
    @JsonProperty("ECGarr")
    public double[] getECGarr() {return ECGarr;}

    @JsonProperty("ECGarr")
    public void setECGarr(double[] ECGarr) {
        this.ECGarr = ECGarr;
    }
}
