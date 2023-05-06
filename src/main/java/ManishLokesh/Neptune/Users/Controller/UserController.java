package ManishLokesh.Neptune.Users.Controller;

import ManishLokesh.Neptune.EmailTrigger.SendSignupOTP;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.Users.Entity.Login;
import ManishLokesh.Neptune.Users.Entity.Signup;
import ManishLokesh.Neptune.Users.Repository.LoginRepo;
import ManishLokesh.Neptune.Users.Repository.SignupRepo;
import ManishLokesh.Neptune.Users.RequestBody.LoginRequestBody;
import ManishLokesh.Neptune.Users.RequestBody.OtpValidateRequestBody;
import ManishLokesh.Neptune.Users.RequestBody.SignupRequestBody;
import ManishLokesh.Neptune.Users.RespondeBody.LoginResponse;
import ManishLokesh.Neptune.Users.RespondeBody.OtpValidateResponse;
import ManishLokesh.Neptune.Users.RespondeBody.SignUpResponse;
import ManishLokesh.Neptune.Users.Service.SignupService;
import jakarta.validation.Valid;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;


@RestController
public class UserController {

    @Autowired
    private SignupRepo signupRepo;
    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private SignupService service;

    private final SendSignupOTP sendSignupOTP = new SendSignupOTP();



    @PostMapping("/api/v1/signup")
    public ResponseEntity<ResponseDTO> newUserSignup(@Valid @RequestBody SignupRequestBody SignupRequestBody){
        return this.service.signup(SignupRequestBody);
    }



    @PostMapping("/api/v1/otp-validate")
    public ResponseEntity<ResponseDTO>otpVerify(@Valid @RequestBody OtpValidateRequestBody otpValidateRequestBody){
        return this.service.otpValidate(otpValidateRequestBody);
    }



    @PostMapping("/api/v1/login")
    public ResponseEntity<?> details(@Valid @RequestBody LoginRequestBody loginRequestBody){

        Login login = loginRepo.findByMobileNumber(loginRequestBody.getMobileNumber());

        if(Objects.equals(login.getMobileNumber(), loginRequestBody.getMobileNumber())
                && Objects.equals(login.getPassword(), loginRequestBody.getPassword())){

            login.setLastLogin(LocalDateTime.now().toString());
            loginRepo.save(login);
            final LoginResponse loginr = new LoginResponse(login.getId(), login.getCreatedAt(), login.getFullName(), login.getEmailId(),
                    login.getMobileNumber(),login.getGender(), login.getUpdatedAt(),login.getLastLogin());
            final ResponseDTO  response = new ResponseDTO();
            response.status = "Success";
            response.error = null;
            response.result = loginr;

            return new ResponseEntity<>(response,
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new SignUpResponse("failure","Incorrect mobile number or Password",
                    "Please enter correct mobile number and password"),HttpStatus.BAD_REQUEST);
        }
    }

}

