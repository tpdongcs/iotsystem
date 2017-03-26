package hcmut.edu.repository.custom;

import hcmut.edu.model.User;

/**
 * Created by bonamana2811 on 3/26/2017.
 */
public interface UserRepositoryCustom {
    public User findByEmail2(String email);
}
