package com.getit.Get.It.api;

import com.getit.Get.It.dto.ProfileDto;

import com.getit.Get.It.exception.GetItException;
import com.getit.Get.It.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Validated
@RequestMapping("/profiles")

public class ProfileAPI {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id ) throws GetItException {

        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDto) throws GetItException {

        return new ResponseEntity<>(profileService.updateProfile(profileDto), HttpStatus.OK);
    }




}
