package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.EmailTrigger.SendSignupOTP;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.Entity.CustomerLogin;
import ManishLokesh.Neptune.v2.customer.Entity.CustomerSignup;
import ManishLokesh.Neptune.v2.customer.Repository.CustLoginRepo;
import ManishLokesh.Neptune.v2.customer.Repository.CustSignupRepo;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustOtpValidateRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoSignupRequestBody;
import ManishLokesh.Neptune.v2.customer.ResponseBody.CustLoginResponseBody;
import ManishLokesh.Neptune.v2.customer.ResponseBody.CustOtpValidateResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.concurrent.CompletableFuture.runAsync;

@Service
public class CustomerServiceImp implements CustomerService {


    @Autowired
    public CustLoginRepo custLoginRepo;
    @Autowired
    public CustSignupRepo signupRepo;
    @Autowired
    public CustSignupRepo custSignupRepo;

    public JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    public CustLoginRepo loginRepo;

    public SendSignupOTP sendSignupOTP = new SendSignupOTP();

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<ResponseDTO> CustomerAuthLogin(CustoLoginRequestBody loginRequestBody) {
        CustomerLogin login = custLoginRepo.findByMobileNumber(loginRequestBody.getMobileNumber());
        if(login != null){
            if(Objects.equals(login.getMobileNumber(), loginRequestBody.getMobileNumber())){
                if (bCryptPasswordEncoder.matches(loginRequestBody.getPassword(),login.getPassword())) {
                    login.setLastLogin(LocalDateTime.now().toString());
                    custLoginRepo.save(login);

                    String token = jwtUtil.generateToken(login.getFullName());

                    CustLoginResponseBody responseBody = new CustLoginResponseBody(login.getId(),
                            login.getFullName(),login.getCreatedAt(),loginRequestBody.getMobileNumber(),
                            login.getEmailId(),token,login.getGender(),login.getUpdatedAt(),login.getLastLogin(),
                            login.getRole());
                    return new ResponseEntity<>(new ResponseDTO("success",null,responseBody),
                            HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(new ResponseDTO("failure",
                            "Incorrect Mobile Number or Password encrpt",null),
                            HttpStatus.BAD_REQUEST);
                }
                }else{
                return new ResponseEntity<>(new ResponseDTO("failure",
                        "Incorrect Mobile Number or Password",null),
                        HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(new ResponseDTO("failure",
                    "Incorrect Mobile Number",null),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<ResponseDTO> newCustomerSignUp(CustoSignupRequestBody requestBody) {
        CustomerSignup exist = signupRepo.findByMobileNumber(requestBody.getMobileNumber());
        if(exist != null){
            CustomerLogin already = loginRepo.findByMobileNumber(requestBody.getMobileNumber());
            if(already != null){
                return new ResponseEntity<>(new ResponseDTO("failure",
                        "Mobile number already exist, Please try with another Mobile Number",
                        null
                ), HttpStatus.BAD_REQUEST);
            }else{
                exist.setFullName(requestBody.getFullName());
                exist.setEmailId(requestBody.getEmailId());
                String password = bCryptPasswordEncoder.encode(requestBody.getPassword());
                exist.setPassword(password);
                exist.setGender(requestBody.getGender());
                exist.setUpdatedAt(LocalDateTime.now().toString());
                String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
                runAsync(() -> sendSignupOTP.sendOTP(requestBody.getEmailId(), otp));
                signupRepo.save(exist);
                return new ResponseEntity<>(new ResponseDTO("success",null,
                        "Otp sent to the Register Email Id"),HttpStatus.OK);
            }
        }else{
            CustomerSignup customerSignup = new CustomerSignup();
            customerSignup.setCreatedAt(LocalDateTime.now().toString());
            customerSignup.setEmailId(requestBody.getEmailId());
            customerSignup.setMobileNumber(requestBody.getMobileNumber());
            customerSignup.setGender(requestBody.getGender());
            customerSignup.setFullName(requestBody.getFullName());

            String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
            customerSignup.setOtp(otp);
            runAsync(() -> sendSignupOTP.sendOTP(requestBody.getEmailId(),otp));

            String password = bCryptPasswordEncoder.encode(requestBody.getPassword());
            customerSignup.setPassword(password);

            signupRepo.save(customerSignup);

            return new ResponseEntity<>(new ResponseDTO("success",null,
                    "Otp sent to the Register Email Id"),HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<ResponseDTO> CustoOtpValidate(CustOtpValidateRequestBody validateRequestBody) {
        CustomerSignup signup = custSignupRepo.findByMobileNumber(validateRequestBody.getMobileNumber());
        if(signup == null){
            return new ResponseEntity<>(new ResponseDTO("failure",
                    "Incorrect Mobile Number, Please Signup",null),
                    HttpStatus.BAD_REQUEST);
        }else{
            if(!Objects.equals(signup.getOtp(), validateRequestBody.getOtp())){
                return new ResponseEntity<>(new ResponseDTO("failure",
                        "Incorrect OTP value",null)
                        , HttpStatus.BAD_REQUEST);
            }else{
                CustomerLogin login = custLoginRepo.findByMobileNumber(validateRequestBody.getMobileNumber());
                if(login == null){
                    CustomerLogin customerLogin = new CustomerLogin();
                    customerLogin.setCreatedAt(LocalDateTime.now().toString());
                    customerLogin.setGender(signup.getGender());
                    customerLogin.setFullName(signup.getFullName());
                    customerLogin.setEmailId(signup.getEmailId());
                    customerLogin.setPassword(signup.getPassword());
                    customerLogin.setMobileNumber(signup.getMobileNumber());
                    customerLogin.setLastLogin(LocalDateTime.now().toString());
                    String role = "CUSTOMER";
                    customerLogin.setRole(role);
                    CustomerLogin login1 = custLoginRepo.saveAndFlush(customerLogin);
                    String token = jwtUtil.generateToken(login1.getFullName());
                    CustOtpValidateResponseBody custOtpValidateResponseBody = new CustOtpValidateResponseBody(login1.getId(),
                            login1.getCreatedAt(),login1.getFullName(),login1.getEmailId(),login1.getMobileNumber(),login1.getGender(),
                            login1.getUpdatedAt(),token,role);
                    return new ResponseEntity<>(new ResponseDTO("success",null,custOtpValidateResponseBody)
                            ,HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>(new ResponseDTO("failure","Account already Created"
                            ,null),HttpStatus.BAD_REQUEST);
                }
            }
        }
    }




}
