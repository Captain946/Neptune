package ManishLokesh.Neptune.v1.Users.Controller;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Users.RequestBody.LoginRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.OtpValidateRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.SignupRequestBody;
import ManishLokesh.Neptune.v1.Users.Service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    private SignupService service;

    @PostMapping("/api/v1/signup")
    public ResponseEntity<ResponseDTO> newUserSignup(@RequestBody SignupRequestBody SignRequestBody){
        return this.service.signup(SignRequestBody);
    }

    @PostMapping("/api/v1/otp-validate")
    public ResponseEntity<ResponseDTO>otpVerify(@Valid @RequestBody OtpValidateRequestBody otpValidateRequestBody){
        return this.service.otpValidate(otpValidateRequestBody);
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> details(@Valid @RequestBody LoginRequestBody loginRequestBody) {
        return this.service.login(loginRequestBody);
    }

}

