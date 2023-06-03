package ManishLokesh.Neptune.v1.Outlets.Service;

import ManishLokesh.Neptune.v1.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface OutletService {

    public ResponseEntity<ResponseDTO> CreateNewOutlet(CreateOutlet createOutlet);

}
