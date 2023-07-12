package ManishLokesh.Neptune.v1.Outlets.Controller;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.v1.Outlets.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.v1.Outlets.Service.OutletService;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OutletController {

    @Autowired
    public OutletService outletService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("api/v1/add/outlet")
    public ResponseEntity<ResponseDTO> addNewOutlet(@RequestBody CreateOutlet createOutlet, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer","");
        if (jwtUtil.validateToken(token)) {
            return this.outletService.CreateNewOutlet(createOutlet);
        }
        return new ResponseEntity<>(new ResponseDTO("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("api/v1/add/menu/outlet/{outletId}")
    public ResponseEntity<ResponseDTO>addNewMenu(@PathVariable String outletId, @RequestBody CreateMenu createMenu, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(jwtUtil.validateToken(token)){
            return this.outletService.CreateNewMenu(Long.parseLong(outletId), createMenu);
        }
        return new ResponseEntity<>(new ResponseDTO("failure","Not authorize to Access",null),HttpStatus.UNAUTHORIZED);
    }

}
