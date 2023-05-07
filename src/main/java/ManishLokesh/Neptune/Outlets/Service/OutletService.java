package ManishLokesh.Neptune.Outlets.Service;

import ManishLokesh.Neptune.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface OutletService {

    public ResponseEntity<ResponseDTO> CreateNewOutlet(CreateOutlet createOutlet);

}
