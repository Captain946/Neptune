package ManishLokesh.Neptune.v1.Menu.Controller;

import ManishLokesh.Neptune.AuthController.JWTToke.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Menu.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.Menu.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    private ResponseDTO responseDTO;

    @Autowired
    private MenuService Service;

    @Autowired
    private JwtUtil jwtUtil;



    @PostMapping("api/v1/add/menu")
    public ResponseEntity<ResponseDTO>addNewMenu(@RequestBody CreateMenu createMenu, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(jwtUtil.validateToken(token)){
            return this.Service.CreateNewMenu(createMenu);
        }
        return new ResponseEntity<>(new ResponseDTO("failure","Not authorize to Access",null),HttpStatus.UNAUTHORIZED);
    }

}
