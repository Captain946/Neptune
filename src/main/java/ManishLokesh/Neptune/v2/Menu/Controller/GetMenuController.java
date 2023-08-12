package ManishLokesh.Neptune.v2.Menu.Controller;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Menu.Service.GetMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class GetMenuController {

    @Autowired
    public GetMenuService service;
    @Autowired
    public JwtUtil jwtUtil;

    @GetMapping("api/v2/outlet/{outlet_Id}/menu")
    public ResponseEntity<ResponseDTO> getMenu(@PathVariable("outlet_Id") String outletId, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer","");
        if(jwtUtil.validateToken(token)){
            return this.service.getActiveMenu(outletId);
        }
        return new ResponseEntity<>(new ResponseDTO("failure","Not authorize to Access",null),HttpStatus.UNAUTHORIZED);

    }
}
