package com.getit.Get.It.api;


import com.getit.Get.It.dto.LoginDTO;
import com.getit.Get.It.dto.ResponseDTO;
import com.getit.Get.It.dto.UserDTO;
import com.getit.Get.It.exception.GetItException;
import com.getit.Get.It.repository.UserRepository;
import com.getit.Get.It.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Validated //Generate method not valid exception
@RequestMapping("/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<UserDTO>registerUser(@RequestBody @Valid UserDTO userDTO) throws GetItException {
        userDTO= userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO>loginUser(@RequestBody @Valid LoginDTO loginDTO ) throws GetItException {
//        userDTO= userService.registerUser(userDTO);
        return new ResponseEntity<>(userService. loginUser(loginDTO), HttpStatus.OK);

    }

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDTO> sendOtp(
            @PathVariable @Email(message = "user.email.invalid") String email) throws Exception {

        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDTO("Otp sent successfully"), HttpStatus.OK);
    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp(
            @PathVariable @Email(message = "user.email.invalid") String email,
            @PathVariable @Pattern(regexp = "^[0-9]{6}$", message = "otp.invalid") String otp
    ) throws GetItException {
        userService.verifyOtp(email, otp);
        return new ResponseEntity<>(new ResponseDTO("OTP verified successfully"), HttpStatus.ACCEPTED);
    }


    @PostMapping("/changePass")
    public ResponseEntity<ResponseDTO>ChangePassword(@RequestBody @Valid LoginDTO loginDTO ) throws GetItException {
//        userDTO= userService.registerUser(userDTO);
        return new ResponseEntity<>(userService.changePassword(loginDTO), HttpStatus.OK);

    }

}

