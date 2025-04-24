package com.getit.Get.It.utility;

import com.getit.Get.It.entity.Sequence;
import com.getit.Get.It.exception.GetItException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;


@Slf4j
@Component
public class Utilities {


    private final MongoOperations mongoOperations;

   public Utilities(MongoOperations mongoOperations) {
       this.mongoOperations=mongoOperations;
   }

    public Long getNextSequence(String key) throws GetItException {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        Sequence seq = mongoOperations.findAndModify(query, update, options, Sequence.class);
        log.info("Fetching next sequence for key: {}", key);

        if (seq == null) throw new GetItException( "Unable to get sequence id of key: "+key);

        return seq.getSeq();
    }

    public static String generateOTP() throws GetItException {
       StringBuilder otp=new StringBuilder();
       SecureRandom random=new SecureRandom();
       for(int i=0;i<6;i++) otp.append(random.nextInt(10));
       return otp.toString();
    }

    }
