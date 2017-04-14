package hcmut.edu.controller;

/**
 * Created by bonamana2811 on 3/24/2017.
 */
import java.util.function.Supplier;

import com.mongodb.util.JSON;
import hcmut.edu.ApplicationRepositoryUtil;
import hcmut.edu.model.ResponseObject;
import hcmut.edu.service.ECGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Sony
 *
 */
@RestController
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    ApplicationRepositoryUtil applicationRepositoryUtil;

    @Resource
    ECGService ECGservice;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public ResponseObject handleRequest(@RequestBody ResponseObject in) {
        System.out
                .println("---------------------Testing method called---------------- Mongo Template :"
                        + applicationRepositoryUtil.getMongoTemplate()
                        + " Repository Object : "
                        + applicationRepositoryUtil.getCustomerRepository());
        Supplier<ResponseObject> supplier = ResponseObject::new;
        ResponseObject responseObject = supplier.get();
        System.out.println(applicationRepositoryUtil.getCustomerRepository().findByEmail2("bona@gmail.com").getPassword());
        responseObject.setMessage("Sample Data Object");
        return in;
    }
    @RequestMapping(value = "/testGet",method = RequestMethod.GET)
    public ResponseObject testGet(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("cc","vv");
        ResponseObject a = new ResponseObject();
        a.setMessage("Get successfully!!");
        return a;
    }
    @RequestMapping(value = "/testPost",method = RequestMethod.POST)
    public ResponseObject testPost(@RequestBody ResponseObject body,HttpServletRequest request){
        return body;
    }

    @RequestMapping(value="/testAsync",method = RequestMethod.GET)
    public ResponseObject testAsync() throws  Exception{
        ResponseObject a = new ResponseObject();
        a.setMessage("Async Start");
        ECGservice.testAsyncThread();
        return a;
    }

}