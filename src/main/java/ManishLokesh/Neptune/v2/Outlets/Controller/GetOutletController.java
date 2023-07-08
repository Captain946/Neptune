package ManishLokesh.Neptune.v2.Outlets.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Outlets.Service.GetOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetOutletController {

    @Autowired
    public GetOutletService getOutletService;

    @GetMapping("/api/v2/outlet/station/{stationCode}")
    public ResponseEntity<ResponseDTO> GetOutlet(@PathVariable String stationCode){

        return this.getOutletService.GetOutletAll(stationCode);
    }
}
