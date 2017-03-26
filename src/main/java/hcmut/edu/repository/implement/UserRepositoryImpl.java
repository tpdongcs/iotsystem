package hcmut.edu.repository.implement;

import hcmut.edu.model.User;
import hcmut.edu.repository.custom.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

/**
 * Created by bonamana2811 on 3/25/2017.
 */

public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public User findByEmail2(String email){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoOperations.findOne(query,User.class);
    }


}
