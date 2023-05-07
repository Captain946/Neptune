package ManishLokesh.Neptune.Users.Service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.concurrent.CompletableFuture.runAsync;

@Service
public class SignupServiceImp implements SignupService{

    @Autowired
    public SignupRepo signupRepo;

    @Autowired
    public LoginRepo loginRepo;
    private final SendSignupOTP sendSignupOTP = new SendSignupOTP();

    @Override
    public ResponseEntity<ResponseDTO> signup(SignupRequestBody requestBody) {
        Signup exist = signupRepo.findByMobileNumber(requestBody.getMobileNumber());

        if(exist != null){
            if(loginRepo.findByMobileNumber(requestBody.getMobileNumber()) != null){
                return new ResponseEntity<>(
                new ResponseDTO<>("failure","Mobile number already " +
                        "exist, Please try with another mobile number",null),HttpStatus.BAD_REQUEST);
            }else{
                exist.setUpdatedAt(LocalDateTime.now().toString());
                String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
                exist.setOtp(otp);
                signupRepo.save(exist);
                runAsync(() -> sendSignupOTP.sendOTP(requestBody.getEmailId(), otp));
                return new ResponseEntity<>(new ResponseDTO<>("success",null,
                        "OTP sent to the registered Email ID"),HttpStatus.OK);
            }
        }
        Signup signup = new Signup();
        signup.setFullName(requestBody.getFullName());
        signup.setGender(requestBody.getGender());
        signup.setEmailId(requestBody.getEmailId());
        signup.setMobileNumber(requestBody.getMobileNumber());
        signup.setPassword(requestBody.getPassword());
        signup.setCreatedAt(LocalDateTime.now().toString());
        String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
        signup.setOtp(otp);
        signupRepo.save(signup);
        runAsync(() -> sendSignupOTP.sendOTP(signup.getEmailId(), otp));
        return new ResponseEntity<>(new ResponseDTO<>("success",null,
                "OTP sent to the registered Email Id"),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> otpValidate(OtpValidateRequestBody otpValidateRequestBody) {

        if(signupRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber()) == null){
            return new ResponseEntity<>(new ResponseDTO("failure","Incorrect mobile number",
                    null),HttpStatus.BAD_REQUEST);
        }else{

            Signup signup = signupRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber());
            if(!Objects.equals(signup.getOtp(), otpValidateRequestBody.getOtp())){

                return new ResponseEntity<>(new ResponseDTO("failure","Incorrect OTP Value",
                        null),HttpStatus.BAD_REQUEST);
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

            OtpValidateResponse res = new OtpValidateResponse(signup.getId(), signup.getCreatedAt(), signup.getFullName(), signup.getEmailId(),
                    signup.getMobileNumber(),signup.getGender(), signup.getUpdatedAt());
            return new ResponseEntity<>(new ResponseDTO("Success",null,res) ,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResponseDTO("failure","Account already Created",
                    null),HttpStatus.BAD_REQUEST);
        }
    }
}
    }

@Override
public ResponseEntity<ResponseDTO> login(LoginRequestBody loginRequestBody) {
        Login login = loginRepo.findByMobileNumber(loginRequestBody.getMobileNumber());
        if(login!= null){
            if(Objects.equals(login.getMobileNumber(), loginRequestBody.getMobileNumber())
                    && Objects.equals(login.getPassword(), loginRequestBody.getPassword())){

                login.setLastLogin(org.joda.time.LocalDateTime.now().toString());
                loginRepo.save(login);
                final LoginResponse res = new LoginResponse(login.getId(), login.getCreatedAt(), login.getFullName(), login.getEmailId(),
                        login.getMobileNumber(),login.getGender(), login.getUpdatedAt(),login.getLastLogin());

                return new ResponseEntity<>(new ResponseDTO("Success",null,res),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ResponseDTO("failure","Incorrect mobile number or Password",
                        null),HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(new ResponseDTO("failure","Incorrect mobile number or Password",
                    null),HttpStatus.BAD_REQUEST);
        }
    }

}
