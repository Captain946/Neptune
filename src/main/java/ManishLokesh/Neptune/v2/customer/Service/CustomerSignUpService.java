package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Users.RequestBody.SignupRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoSignupRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface CustomerSignUpService  {
    public ResponseEntity<ResponseDTO> newCustomerSignUp(CustoSignupRequestBody requestBody);


}
