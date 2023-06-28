package ManishLokesh.Neptune.v2.customer.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustOtpValidateRequestBody;
import ManishLokesh.Neptune.v2.customer.Service.CustomerOtpValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OtpValidateController {

    @Autowired
    public CustomerOtpValidateService validateService;



    @PostMapping("/api/v2/otp-validate")
    public ResponseEntity<ResponseDTO> valdateOtp(@RequestBody CustOtpValidateRequestBody otpValidateRequestBody){
        return this.validateService.CustoOtpValidate(otpValidateRequestBody);
    }
}


