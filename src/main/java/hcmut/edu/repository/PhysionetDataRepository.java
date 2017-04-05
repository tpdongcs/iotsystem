package hcmut.edu.repository;

import hcmut.edu.model.User;
import hcmut.edu.repository.custom.UserRepositoryCustom;
import net.physionet.PhysionetData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by bonamana2811 on 4/4/2017.
 */
@RepositoryRestResource(collectionResourceRel="physionetDB",path="physionetDB")
public interface PhysionetDataRepository extends MongoRepository<PhysionetData, String>,UserRepositoryCustom {

}
