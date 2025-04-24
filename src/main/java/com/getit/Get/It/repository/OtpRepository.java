package com.getit.Get.It.repository;

import com.getit.Get.It.entity.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OtpRepository extends MongoRepository<OTP,String> {
    List<OTP> findByCreationTimeBefore(LocalDateTime expiry);


}
