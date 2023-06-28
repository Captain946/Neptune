package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustOtpValidateRequestBody;
import org.springframework.http.ResponseEntity;

public interface CustomerOtpValidateService {
    public ResponseEntity<ResponseDTO> CustoOtpValidate(CustOtpValidateRequestBody validateRequestBody);
}
