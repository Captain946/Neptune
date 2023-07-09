package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.Entity.Customer;
import ManishLokesh.Neptune.v2.customer.Repository.CustLoginRepo;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import ManishLokesh.Neptune.v2.customer.ResponseBody.CustLoginResponseBody;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CustomerLoginServiceImp implements CustomerLoginService{


    @Autowired
    public CustLoginRepo custLoginRepo;

    private final JwtUtil jwtUtil = new JwtUtil();
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public ResponseEntity<ResponseDTO> CustomerAuthLogin(CustoLoginRequestBody loginRequestBody) {
        Customer login = custLoginRepo.findByMobileNumber(loginRequestBody.getMobileNumber());
        if(login != null){
            if(Objects.equals(login.getMobileNumber(), loginRequestBody.getMobileNumber())){
                if (bCryptPasswordEncoder.matches(loginRequestBody.getPassword(),login.getPassword())) {
                    login.setLastLogin(LocalDateTime.now().toString());
                    custLoginRepo.save(login);

                    String token = jwtUtil.generateToken(login.getFullName());

                    DateTime ist = DateTime.parse(login.getLastLogin());
                    System.out.println(ist);

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
}
