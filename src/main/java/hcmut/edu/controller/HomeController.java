package hcmut.edu.controller;

/**
 * Created by bonamana2811 on 3/24/2017.
 */
import java.util.function.Supplier;

import hcmut.edu.ApplicationRepositoryUtil;
import hcmut.edu.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sony
 *
 */
@RestController
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    ApplicationRepositoryUtil applicationRepositoryUtil;

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
}