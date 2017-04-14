package hcmut.edu.controller;

import hcmut.edu.ApplicationRepositoryUtil;
import hcmut.edu.model.ErrorMessage;
import hcmut.edu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HuyNho on 4/7/2017.
 */
@RestController
public class AuthenticationController {

    @Autowired
    ApplicationRepositoryUtil applicationRepositoryUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user){
        ErrorMessage errorMessage = new ErrorMessage("Login Failed.");
        if(user == null)
            return new ResponseEntity(errorMessage,HttpStatus.BAD_REQUEST);
        //Validate Email
        if(!ValidateEmail(user.getEmail()))
            return new ResponseEntity(errorMessage,HttpStatus.UNAUTHORIZED);
        //Check DB have this user
        User userDB = applicationRepositoryUtil.getCustomerRepository().findByEmail2(user.getEmail());
        if(userDB == null)
            return new ResponseEntity(errorMessage,HttpStatus.NOT_FOUND);
        //Check password of this user.
        if(userDB.getPassword().equals(HashMD5(user.getPassword())))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(errorMessage,HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody User user){
        if(user == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        //Validate Email
        if(!ValidateEmail(user.getEmail()) || !ValidatePassword(user.getPassword()))
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        //Check user existed in DB
        User userDB = applicationRepositoryUtil.getCustomerRepository().findByEmail2(user.getEmail());
        if(userDB != null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        //Create a new User
        user.setPassword(HashMD5(user.getPassword()));
        applicationRepositoryUtil.getCustomerRepository().save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    private static String HashMD5(String item){
        MessageDigest md = null;
        String itemHash = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(item.getBytes());
            byte[] digest = md.digest();
            itemHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            //
        }
        return itemHash;
    }

    private boolean ValidateEmail(String email){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean ValidatePassword(String password){
        Pattern VALID_PASSWORD_REGEX =
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
