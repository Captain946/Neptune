package ManishLokesh.Neptune.v2.customer.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoSignupRequestBody;
import ManishLokesh.Neptune.v2.customer.Service.CustomerSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    public CustomerSignUpService customerSignUpService;

    @PostMapping("/api/v2/signup")
    public ResponseEntity<ResponseDTO> customerSignUp(@RequestBody CustoSignupRequestBody newCustomerSignup){
        return this.customerSignUpService.newCustomerSignUp(newCustomerSignup);
    }
}
