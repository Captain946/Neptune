package ManishLokesh.Neptune.v2.Menu.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Menu.Service.GetMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetMenuController {

    @Autowired
    public GetMenuService service;

    @GetMapping("api/v2/outlet/{outlet_Id}/menu")
    public ResponseEntity<ResponseDTO> getMenu(@PathVariable("outlet_Id") String outletId){
        return this.service.getActiveMenu(outletId);
    }
}
