package ManishLokesh.Neptune.Outlets.Controller;

import ManishLokesh.Neptune.Outlets.Repository.OutletRepo;
import ManishLokesh.Neptune.Outlets.RequestBody.CreateOutlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutletController {

    @Autowired
    private OutletRepo outletRepo;



    @PostMapping("api/v1/outlet")
    public ResponseEntity<?>addOutlet(@RequestBody CreateOutlet createOutlet){
        return null;
    }
}
