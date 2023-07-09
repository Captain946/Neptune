package ManishLokesh.Neptune.v2.customer.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import ManishLokesh.Neptune.v2.customer.Service.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerLoginController {

    @Autowired
    public CustomerLoginService customerLoginService;

    @PostMapping("/api/v2/auth/login")
    public ResponseEntity<ResponseDTO> CustomerLogin(@RequestBody CustoLoginRequestBody loginRequestBody){
        return this.customerLoginService.CustomerAuthLogin(loginRequestBody);
    }
}
