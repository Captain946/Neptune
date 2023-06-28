package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginServiceImp implements CustomerLoginService{
    @Override
    public ResponseEntity<ResponseDTO> CustomerAuthLogin(CustoLoginRequestBody loginRequestBody) {
        return new ResponseEntity<>(new ResponseDTO("success",null,
                "service class are working"), HttpStatus.OK);
    }
}
