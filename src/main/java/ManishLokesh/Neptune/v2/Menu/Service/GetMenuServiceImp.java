package ManishLokesh.Neptune.v2.Menu.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Outlets.Entity.Menu;
import ManishLokesh.Neptune.v2.Menu.Repository.GetMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMenuServiceImp implements GetMenuService {

    @Autowired
    public GetMenuRepo getMenuRepo;

    @Override
    public ResponseEntity<ResponseDTO> getActiveMenu(String outletId) {
        List<Menu> menuList = getMenuRepo.findByOutletId(outletId);
        return new ResponseEntity<>(new ResponseDTO("success",null,menuList),
                HttpStatus.OK);
    }
}
