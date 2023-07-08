package ManishLokesh.Neptune.v2.Outlets.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Outlets.Service.GetOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetOutletController {

    @Autowired
    public GetOutletService getOutletService;

    @GetMapping("/api/v2/outlets")
    public ResponseEntity<ResponseDTO> GetOutlet(){

        return this.getOutletService.GetOutletAll();
    }
}
