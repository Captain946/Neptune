package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.EmailTrigger.SendSignupOTP;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.Entity.Customer;
import ManishLokesh.Neptune.v2.customer.Entity.CustomerSignup;
import ManishLokesh.Neptune.v2.customer.Repository.CustLoginRepo;
import ManishLokesh.Neptune.v2.customer.Repository.CustSignupRepo;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoSignupRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.concurrent.CompletableFuture.runAsync;


@Service
public class CustomerSignUpServiceImp implements CustomerSignUpService{

    @Autowired
    public CustSignupRepo signupRepo;

    @Autowired
    public CustLoginRepo loginRepo;

    public SendSignupOTP sendSignupOTP = new SendSignupOTP();

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<ResponseDTO> newCustomerSignUp(CustoSignupRequestBody requestBody) {
        CustomerSignup exist = signupRepo.findByMobileNumber(requestBody.getMobileNumber());
        if(exist != null){
            Customer already = loginRepo.findByMobileNumber(requestBody.getMobileNumber());
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
}

