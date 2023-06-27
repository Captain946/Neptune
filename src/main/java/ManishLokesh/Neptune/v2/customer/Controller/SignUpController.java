package ManishLokesh.Neptune.v2.customer.Controller;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.SignupRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {


    @PostMapping("/api/v2/signup")
    public ResponseEntity<ResponseDTO> customerSignUp(@RequestBody SignupRequestBody signupRequestBody){
        return new ResponseEntity<>(new ResponseDTO("success","no error are shown",null), HttpStatus.CREATED);
    }
}
