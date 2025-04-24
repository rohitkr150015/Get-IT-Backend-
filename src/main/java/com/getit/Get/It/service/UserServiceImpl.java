package com.getit.Get.It.service;

import com.getit.Get.It.dto.LoginDTO;
import com.getit.Get.It.dto.ResponseDTO;
import com.getit.Get.It.dto.UserDTO;
import com.getit.Get.It.entity.OTP;
import com.getit.Get.It.entity.User;
import com.getit.Get.It.exception.GetItException;
import com.getit.Get.It.repository.OtpRepository;
import com.getit.Get.It.repository.UserRepository;
import com.getit.Get.It.utility.Data;
import com.getit.Get.It.utility.Utilities;



import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
private UserRepository userRepository;
    @Autowired
    private Utilities utilities;

    @Autowired
     private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileServiceImpl profileServiceImpl;


    @Override
    public UserDTO registerUser(UserDTO userDto) throws GetItException {
        Optional<User> optionalUser= userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent())throw new GetItException("User already exists");
        Long userId =utilities.getNextSequence("users");
        userDto.setId(userId);
        userDto.setProfileId(profileService.createProfile(userDto.getEmail()));
//        userDto.setId(profileService.createProfile(userDto.getEmail()));
//        userDto.setProfileId(utilities.getNextSequence("profiles"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user=userDto.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws GetItException {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new GetItException("USER_NOT_FOUND"));

        // Fix: Throw exception only if password does not match
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new GetItException("INVALID_CREDENTIALS");

        return user.toDTO();
    }

//    @Override
//    public UserDTO loginUser(LoginDTO loginDTO) throws GetItException {
//        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new GetItException("USER_NOT_FOUND"));
//        if (passwordEncoder.matches(loginDTO.getPassword(),user.getPassword())) throw new GetItException("INVALID_CREDENTIALS");
//        return user.toDTO();
//    }

    @Override
    public boolean sendOtp(String email) throws Exception {

        User user=userRepository.findByEmail(email).orElseThrow(()->new GetItException("USER_NOT_FOUND"));

        MimeMessage mm=mailSender.createMimeMessage();
        MimeMessageHelper message=new MimeMessageHelper(mm,true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
         String generatedOTP=Utilities.generateOTP();
         OTP otp=new OTP(email,generatedOTP, LocalDateTime.now());
         otpRepository.save(otp);
         message.setText(Data.getMessageBody(generatedOTP,user.getName()),true);
         mailSender.send(mm);;
        return true;
    }

    public boolean verifyOtp(String email , String otp) throws GetItException {
        OTP otpEntity=otpRepository.findById(email).orElseThrow(()->new GetItException("OTP Expired"));
        if(!otpEntity.getOtp().equals(otp))throw new GetItException("INVALID_OTP");
        return true;


    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws GetItException {
       User user= userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new GetItException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);

        return new ResponseDTO("Password changed successfully");
    }

    @Scheduled(fixedRate = 60000)
    public void removeExpriredOTPs()  {

        LocalDateTime expiry=LocalDateTime.now().minusMinutes(5);
        List<OTP>expiredOTPs=otpRepository.findByCreationTimeBefore(expiry);
        if(!expiredOTPs.isEmpty()){
            otpRepository.deleteAll(expiredOTPs);;
            System.out.println("Removed" +expiredOTPs.size()+ " expired OTPs");
        }



    }

}
