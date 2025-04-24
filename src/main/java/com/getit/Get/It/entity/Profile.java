package com.getit.Get.It.entity;

import com.getit.Get.It.dto.Certification;
import com.getit.Get.It.dto.Experience;
import com.getit.Get.It.dto.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profiles")
public class Profile {

    private Long id;
    private String email;
    private String jobTitle;

    private String company;
    private String location;
    private String about;

    private List<String> skills;

    private List<Experience>experience;

    private List<Certification>certification;

    public ProfileDto toDto() {
        return new ProfileDto(this.id,this.email,this.jobTitle,this.company,this.location,this.about,this.skills,this.experience,this.certification);
    }



}
