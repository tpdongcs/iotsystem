package hcmut.edu.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by bonamana2811 on 4/14/2017.
 */
@Service("ECGService")
public class ECGService {
    @Async
    public String testAsyncThread() throws Exception{
        System.out.println("start Async");
        Thread.sleep(10000);
        System.out.println("end Async");
        return "end";
    }
}
