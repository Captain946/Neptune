package ManishLokesh.Neptune.Users.Controller;

import ManishLokesh.Neptune.Users.Entity.Login;
import ManishLokesh.Neptune.Users.Entity.Signup;
import ManishLokesh.Neptune.Users.Repository.LoginRepo;
import ManishLokesh.Neptune.Users.Repository.SignupRepo;
import ManishLokesh.Neptune.Users.RequestBody.OtpValidateRequestBody;
import ManishLokesh.Neptune.Users.RequestBody.SignupRequestBody;
import ManishLokesh.Neptune.Users.RespondeBody.OtpValidateResponse;
import ManishLokesh.Neptune.Users.RespondeBody.SignUpResponse;
import jakarta.validation.Valid;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
public class SignupController {

    @Autowired
    private SignupRepo signupRepo;
    @Autowired
    private LoginRepo loginRepo;



    @PostMapping("/api/v1/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestBody SignupRequestBody){
        Signup exist = signupRepo.findByMobileNumber(SignupRequestBody.getMobileNumber());
        if(exist != null){
            if(loginRepo.findByMobileNumber(SignupRequestBody.getMobileNumber()) != null){
                return new ResponseEntity<>(new SignUpResponse("failure","User already " +
                        "exist with this mobile Number","Please login with same mobile number"), HttpStatus.BAD_REQUEST);
            }else{
                exist.setUpdatedAt(LocalDateTime.now().toString());
                exist.setOtp(String.valueOf((int) (Math.random() * 9000) + 1000));
                signupRepo.save(exist);
                return new ResponseEntity<>(new SignUpResponse("success","user are created successfully",
                        "OTP sent to the registered mobile number"), HttpStatus.CREATED);
            }
        }
        Signup signup = new Signup();
        signup.setFullName(SignupRequestBody.getFullName());
        signup.setGender(SignupRequestBody.getGender());
        signup.setEmailId(SignupRequestBody.getEmailId());
        signup.setMobileNumber(SignupRequestBody.getMobileNumber());
        signup.setPassword(SignupRequestBody.getPassword());
        signup.setCreatedAt(LocalDateTime.now().toString());
        signup.setOtp(String.valueOf((int) (Math.random() * 9000) + 1000));
        signupRepo.save(signup);
        return new ResponseEntity<>(new SignUpResponse("success","user are created successfully",
                "OTP sent to the registered mobile number"), HttpStatus.CREATED);
    }



    @PostMapping("/api/v1/otp-validate")
    public ResponseEntity<?>otpVerify(@Valid @RequestBody OtpValidateRequestBody otpValidateRequestBody){

        if(signupRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber()) == null){
            return new ResponseEntity<>(new SignUpResponse("failure","mobile number is not valid",
                    "Please create an account"),HttpStatus.BAD_REQUEST);
        }else{

            Signup signup = signupRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber());
            if(!Objects.equals(signup.getOtp(), otpValidateRequestBody.getOtp())){

                return new ResponseEntity<>(new SignUpResponse("failure","Incorrect Otp Value",
                        "Please enter Correct OTP"),HttpStatus.BAD_REQUEST);
            }else{
                Login mobile = loginRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber());
                if(mobile == null){
                    Login login = new Login();
                    login.setFullName(signup.getFullName());
                    login.setMobileNumber(signup.getMobileNumber());
                    login.setEmailId(signup.getEmailId());
                    login.setGender(signup.getGender());
                    login.setPassword(signup.getPassword());
                    login.setCreatedAt(LocalDateTime.now().toString());
                    loginRepo.save(login);
                    return new ResponseEntity<>(new OtpValidateResponse("success", "Login Successfully",
                            "",signup.getId(), signup.getCreatedAt(), signup.getFullName(), signup.getEmailId(),
                            signup.getMobileNumber(),signup.getGender(), signup.getUpdatedAt()),
                            HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(new SignUpResponse("failure","Account already Validate",
                            "Please Login with your password"),HttpStatus.BAD_REQUEST);
                }
            }
        }
    }



    @GetMapping("/details")
    public String details(@RequestParam String number){
        Signup signup = signupRepo.findByMobileNumber(number);
        return signup.getFullName();
    }

}

