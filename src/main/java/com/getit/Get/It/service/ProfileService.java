package com.getit.Get.It.service;

import com.getit.Get.It.dto.ProfileDto;
import com.getit.Get.It.exception.GetItException;

public interface ProfileService {
    public Long createProfile(String email) throws GetItException;

    public ProfileDto getProfile(Long id) throws GetItException;

    public ProfileDto updateProfile(ProfileDto profileDto) throws GetItException;
}
