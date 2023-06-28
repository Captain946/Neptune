package ManishLokesh.Neptune.v1.Users.Service;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.EmailTrigger.SendSignupOTP;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Users.Entity.Login;
import ManishLokesh.Neptune.v1.Users.Entity.Signup;
import ManishLokesh.Neptune.v1.Users.Repository.LoginRepo;
import ManishLokesh.Neptune.v1.Users.Repository.SignupRepo;
import ManishLokesh.Neptune.v1.Users.RequestBody.LoginRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.OtpValidateRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.SignupRequestBody;
import ManishLokesh.Neptune.v1.Users.RespondeBody.LoginResponse;
import ManishLokesh.Neptune.v1.Users.RespondeBody.OtpValidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.concurrent.CompletableFuture.runAsync;

@Service
public class SignupServiceImp implements SignupService{

    @Autowired
    public SignupRepo signupRepo;

    @Autowired
    public LoginRepo loginRepo;

    @Autowired
    private JwtUtil jwtUtil;

    private final SendSignupOTP sendSignupOTP = new SendSignupOTP();
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String password = bCryptPasswordEncoder.encode(requestBody.getPassword());
                exist.setPassword(password);
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
        //signup.setPassword(requestBody.getPassword());
        signup.setCreatedAt(LocalDateTime.now().toString());
        String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
        signup.setOtp(otp);
        String password = bCryptPasswordEncoder.encode(requestBody.getPassword());
        signup.setPassword(password);
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
                    String userRole = "ADMIN";
                    login.setRole(userRole);
                    Login login1 = loginRepo.saveAndFlush(login);
                    String token = jwtUtil.generateToken(login1.getFullName());
                    OtpValidateResponse res = new OtpValidateResponse(login1.getId(), login1.getCreatedAt(), login1.getFullName(), login1.getEmailId(),
                            login1.getMobileNumber(),login1.getGender(), login1.getUpdatedAt(),token,userRole);

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
    if (login != null) {
        //if(Objects.equals(login.getMobileNumber(), loginRequestBody.getMobileNumber())
        //&& Objects.equals(login.getPassword(), loginRequestBody.getPassword())){
        if (Objects.equals(login.getMobileNumber(), loginRequestBody.getMobileNumber())) {
            if (bCryptPasswordEncoder.matches(loginRequestBody.getPassword(), login.getPassword())) {
                login.setLastLogin(LocalDateTime.now().toString());
                loginRepo.save(login);
                String token = jwtUtil.generateToken(login.getFullName());
                final LoginResponse res = new LoginResponse(login.getId(), login.getCreatedAt(),
                        login.getFullName(), login.getEmailId(),
                        login.getMobileNumber(), login.getGender(), login.getUpdatedAt(),
                        login.getLastLogin(),token,login.getRole());

                return new ResponseEntity<>(new ResponseDTO("Success", null, res), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDTO("failure", "Incorrect mobile number or Password",
                        null), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseDTO("failure", "Incorrect mobile number or Password",
                    null), HttpStatus.BAD_REQUEST);
        }
    }else{
    return new ResponseEntity<>(new ResponseDTO("failure", "Incorrect mobile number",
            null), HttpStatus.BAD_REQUEST);
}
    }
}
