package ManishLokesh.Neptune.v2.customer.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Users.RequestBody.OtpValidateRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OtpValidateController {

    @PostMapping("/api/v2/otp-validate")
    public ResponseEntity<ResponseDTO> valdateOtp(@RequestBody OtpValidateRequestBody otpValidateRequestBody){
        return new ResponseEntity<>(new ResponseDTO("success",null,null), HttpStatus.OK);
    }
}


