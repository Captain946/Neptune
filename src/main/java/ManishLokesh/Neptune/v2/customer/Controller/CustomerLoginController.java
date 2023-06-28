package ManishLokesh.Neptune.v2.customer.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Users.RequestBody.LoginRequestBody;
import ManishLokesh.Neptune.v2.customer.Entity.CustomerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerLoginController {

    @PostMapping("/api/v2/auth/login")
    public ResponseEntity<ResponseDTO> CustomerLogin(@RequestBody LoginRequestBody loginRequestBody){
        return new ResponseEntity<>(new ResponseDTO("success",null,null), HttpStatus.OK);
    }
}
