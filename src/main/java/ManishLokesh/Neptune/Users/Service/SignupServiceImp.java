package ManishLokesh.Neptune.Users.Service;

import ManishLokesh.Neptune.EmailTrigger.SendSignupOTP;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.Users.Entity.Signup;
import ManishLokesh.Neptune.Users.Repository.LoginRepo;
import ManishLokesh.Neptune.Users.Repository.SignupRepo;
import ManishLokesh.Neptune.Users.RequestBody.SignupRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                new ResponseDTO<>("failure","User already " +
                        "exist with this mobile Number","Please login with same mobile number"),HttpStatus.BAD_REQUEST);
            }else{
                exist.setUpdatedAt(LocalDateTime.now().toString());
                String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
                exist.setOtp(otp);
                signupRepo.save(exist);
                runAsync(() -> sendSignupOTP.sendOTP(requestBody.getEmailId(), otp));
                return new ResponseEntity<>(new ResponseDTO<>("success","user are created successfully",
                        "OTP sent to the registered mobile number"),HttpStatus.OK);
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
        return new ResponseEntity<>(new ResponseDTO<>("success","user are created successfully",
                "OTP sent to the registered mobile number"),HttpStatus.OK);

    }
}
