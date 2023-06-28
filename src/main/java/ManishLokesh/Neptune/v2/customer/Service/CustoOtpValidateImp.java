package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.Entity.CustomerLogin;
import ManishLokesh.Neptune.v2.customer.Entity.CustomerSignup;
import ManishLokesh.Neptune.v2.customer.Repository.CustLoginRepo;
import ManishLokesh.Neptune.v2.customer.Repository.CustSignupRepo;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustOtpValidateRequestBody;
import ManishLokesh.Neptune.v2.customer.ResponseBody.CustOtpValidateResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CustoOtpValidateImp implements CustomerOtpValidateService{

    @Autowired
    public CustSignupRepo custSignupRepo;

    @Autowired
    public CustLoginRepo custLoginRepo;

    public JwtUtil jwtUtil = new JwtUtil();

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
