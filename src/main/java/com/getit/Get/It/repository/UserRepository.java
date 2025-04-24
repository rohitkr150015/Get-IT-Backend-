package com.getit.Get.It.repository;


import com.getit.Get.It.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {

     Optional<User> findByEmail(String email);

}
