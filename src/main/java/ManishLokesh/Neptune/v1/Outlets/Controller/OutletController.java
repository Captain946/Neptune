package ManishLokesh.Neptune.v1.Outlets.Controller;

import ManishLokesh.Neptune.AuthController.JWTToke.JwtUtil;
import ManishLokesh.Neptune.v1.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.v1.Outlets.Service.OutletService;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutletController {

    private ResponseDTO responseDTO;

    @Autowired
    public OutletService service;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("api/v1/add/outlet")
    public ResponseEntity<ResponseDTO> addNewOutlet(@RequestBody CreateOutlet createOutlet, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer","");
        if (jwtUtil.validateToken(token)) {
            return this.service.CreateNewOutlet(createOutlet);
        }
        return new ResponseEntity<>(new ResponseDTO("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);

    }
}
