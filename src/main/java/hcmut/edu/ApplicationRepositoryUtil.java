package hcmut.edu;

import hcmut.edu.repository.PhysionetDataRepository;
import hcmut.edu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by bonamana2811 on 3/24/2017.
 */

@Service
public class ApplicationRepositoryUtil {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;

    public PhysionetDataRepository getPhysionetDataRepository() {
        return physionetDataRepository;
    }

    @Autowired
    private PhysionetDataRepository physionetDataRepository;

    /**
     * @return the mongoTemplate
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * @return the customerRepository
     */
    public UserRepository getCustomerRepository() {
        return userRepository;
    }

}
