package com.getit.Get.It.entity;

import com.getit.Get.It.dto.AccountType;
import com.getit.Get.It.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;


@Document(collection="users")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;


    private String name;
    @Indexed(unique=true)
    private String email;
    private String password;
    private AccountType accountType;
    private Long profileId;


    public UserDTO toDTO(){{
    return new UserDTO(this.id,this.name,this.email,this.password,this.accountType,this.profileId);

    }
    }


}
