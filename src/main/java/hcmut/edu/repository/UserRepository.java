package hcmut.edu.repository;

/**
 * Created by bonamana2811 on 3/24/2017.
 */

import hcmut.edu.model.User;
import hcmut.edu.repository.custom.UserRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Sony
 *
 */
@RepositoryRestResource(collectionResourceRel="users",path="users")
public interface UserRepository extends MongoRepository<User, String>,UserRepositoryCustom {
    @Query("{email:?0}")
    public User findByEmail1(String email);
}
