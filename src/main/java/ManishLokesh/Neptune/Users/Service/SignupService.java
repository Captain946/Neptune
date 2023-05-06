package ManishLokesh.Neptune.Users.Service;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.Users.RequestBody.OtpValidateRequestBody;
import ManishLokesh.Neptune.Users.RequestBody.SignupRequestBody;
import org.springframework.http.ResponseEntity;


public interface SignupService {

    public ResponseEntity<ResponseDTO> signup(SignupRequestBody requestBody);

    public ResponseEntity<ResponseDTO> otpValidate(OtpValidateRequestBody otpValidateRequestBody);

}
