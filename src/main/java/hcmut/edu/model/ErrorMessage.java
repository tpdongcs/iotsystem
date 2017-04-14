package hcmut.edu.model;

/**
 * Created by HuyNho on 13/4/2017.
 */
public class ErrorMessage {
    private String message;
    public ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){return this.message;}

    public void setMessage(String message){
        this.message = message;
    }
}
