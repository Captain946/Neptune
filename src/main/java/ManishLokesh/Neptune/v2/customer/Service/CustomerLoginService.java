package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import org.springframework.http.ResponseEntity;

public interface CustomerLoginService {

    public ResponseEntity<ResponseDTO>CustomerAuthLogin(CustoLoginRequestBody loginRequestBody);
}
