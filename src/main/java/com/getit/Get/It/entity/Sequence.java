package com.getit.Get.It.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sequences")
public class Sequence {

    @Id
    private String id;

    private Long seq;

}
