package ManishLokesh.Neptune.Outlets.Controller;

import ManishLokesh.Neptune.Outlets.ReponseBody.CreateOutletResponse;
import ManishLokesh.Neptune.Outlets.Repository.OutletRepo;
import ManishLokesh.Neptune.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutletController {

    @Autowired
    private OutletRepo outletRepo;
    private ResponseDTO responseDTO;



    @PostMapping("api/v1/outlet")
    public ResponseEntity<?>addOutlet(@RequestBody CreateOutlet createOutlet){
        //CreateOutletResponse res = new CreateOutletResponse();
        //responseDTO.status = "success";
        //responseDTO.message = "outlet created successfully";
        //responseDTO.result = res;
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
