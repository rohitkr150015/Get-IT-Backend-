package com.getit.Get.It.service;

import com.getit.Get.It.dto.LoginDTO;
import com.getit.Get.It.dto.ResponseDTO;
import com.getit.Get.It.dto.UserDTO;
import com.getit.Get.It.exception.GetItException;


public interface UserService {

    public UserDTO registerUser(UserDTO  userDto) throws GetItException;

    public UserDTO loginUser(LoginDTO loginDTO) throws GetItException;
    public boolean sendOtp(String email) throws Exception;

     public boolean verifyOtp(String email , String otp) throws GetItException;

    ResponseDTO changePassword( LoginDTO loginDTO) throws GetItException;
}
