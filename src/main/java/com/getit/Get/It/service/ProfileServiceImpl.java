package com.getit.Get.It.service;


import com.getit.Get.It.dto.ProfileDto;
import com.getit.Get.It.entity.Profile;
import com.getit.Get.It.exception.GetItException;
import com.getit.Get.It.repository.ProfileRepository;
import com.getit.Get.It.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
private Utilities utilities;
    @Autowired
    private ProfileRepository profileRepository;





    @Override
    public Long createProfile(String email) throws GetItException {
        Profile  profile=new Profile();
        profile.setId(utilities.getNextSequence("profiles"));
        profile.setEmail(email);;
        profile.setSkills(new ArrayList<>());
        profile.setCertification(new ArrayList<>());
        profile.setCertification(new ArrayList<>());
         profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDto getProfile(Long id) throws GetItException {
        return profileRepository.findById(id).orElseThrow(()->new GetItException("Profile Not Found")).toDto();
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto) throws GetItException {
       profileRepository.findById(profileDto.toEntity().getId()).orElseThrow(()->new GetItException("Profile Not Found")).toDto();
        profileRepository.save(profileDto.toEntity());

        return profileDto.toEntity().toDto();

    }
}
