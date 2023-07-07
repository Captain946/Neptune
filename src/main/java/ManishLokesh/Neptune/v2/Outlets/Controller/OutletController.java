package ManishLokesh.Neptune.v2.Outlets.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Outlets.Service.GetOutletService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OutletController {

    public GetOutletService getOutletService;

    @GetMapping("/api/v2/outlets")
    public ResponseEntity<ResponseDTO> GetOutlet(@RequestParam String StationCode){
        return this.getOutletService.GetOutletAll();
    }
}
