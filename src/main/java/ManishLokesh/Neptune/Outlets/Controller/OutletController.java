package ManishLokesh.Neptune.Outlets.Controller;

import ManishLokesh.Neptune.Outlets.ReponseBody.CreateOutletResponse;
import ManishLokesh.Neptune.Outlets.Repository.OutletRepo;
import ManishLokesh.Neptune.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.Outlets.Service.OutletService;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutletController {

    private ResponseDTO responseDTO;

    @Autowired
    public OutletService service;


    @PostMapping("api/v1/outlet")
    public ResponseEntity<ResponseDTO> addNewOutlet(@RequestBody CreateOutlet createOutlet){
        return this.service.CreateNewOutlet(createOutlet);
    }
}
