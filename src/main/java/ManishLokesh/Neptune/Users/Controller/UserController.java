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
    public ResponseEntity<?> details(@Valid @RequestBody LoginRequestBody loginRequestBody) {
        return this.service.login(loginRequestBody);
    }

}

