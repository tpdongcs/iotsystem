package hcmut.edu.controller;

import hcmut.edu.model.ECGObject;
import hcmut.edu.model.ResponseObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by HuyNho on 4/4/2017.
 */
@RestController
public class ECGController {
    @RequestMapping(value = "/sendECG",method = RequestMethod.POST)
    public ResponseObject sendECG(@RequestBody ECGObject ecgObject){
        ResponseObject returnObject = new ResponseObject();
        returnObject.setMessage("Post ECG Succesfully!!!!");
        return returnObject;
    }
}
