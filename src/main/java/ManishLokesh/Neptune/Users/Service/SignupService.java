package ManishLokesh.Neptune.Users.Service;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.Users.RequestBody.SignupRequestBody;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


public interface SignupService {

    public ResponseEntity<ResponseDTO> signup(SignupRequestBody requestBody);

}
