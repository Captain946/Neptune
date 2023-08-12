package ManishLokesh.Neptune.v2.Outlets.Controller;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Outlets.Service.GetOutletService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class GetOutletController {

    @Autowired
    public GetOutletService getOutletService;
    @Autowired
    public JwtUtil jwtUtil;

    @GetMapping("/api/v2/outlet/station/{stationCode}")
    public ResponseEntity<ResponseDTO> GetOutlet(@PathVariable String stationCode, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer","");
        if (jwtUtil.validateToken(token)){
            return this.getOutletService.GetOutletAll(stationCode);
        }
        return new ResponseEntity<>(new ResponseDTO("failure","Not authorize to Access",null), HttpStatus.UNAUTHORIZED);
    }
}
