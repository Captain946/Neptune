package ManishLokesh.Neptune.v1.Menu.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Menu.RequestBody.CreateMenu;
import org.springframework.http.ResponseEntity;

public interface MenuService {
    public ResponseEntity<ResponseDTO> CreateNewMenu(Long outletId,CreateMenu createMenu);
}
