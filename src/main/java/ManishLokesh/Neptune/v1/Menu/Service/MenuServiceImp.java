package ManishLokesh.Neptune.v1.Menu.Service;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Menu.Entity.Menu;
import ManishLokesh.Neptune.v1.Menu.Repository.MenuRepo;
import ManishLokesh.Neptune.v1.Menu.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.Menu.ResponseBody.MenuResponse;
import ManishLokesh.Neptune.v1.Outlets.Repository.OutletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.Long.valueOf;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private OutletRepo outletRepo;

    @Override
    public ResponseEntity<ResponseDTO> CreateNewMenu(CreateMenu createMenu) {
        if(outletRepo.findById(valueOf(createMenu.getOutletId())).isPresent()){
            System.out.println(Float.parseFloat(createMenu.getBasePrice()));
            if(((Float.parseFloat(createMenu.getBasePrice())) * 0.05) == Float.parseFloat(createMenu.getTax())){
                if(((Float.parseFloat(createMenu.getBasePrice()) + Float.parseFloat(createMenu.getTax()))
                        == Float.parseFloat(createMenu.getSellingPrice()))){
                    Menu menu = new Menu();
                    menu.setCreatedAt(LocalDateTime.now().toString());
                    menu.setActive(false);
                    menu.setOutletId(createMenu.getOutletId());
                    menu.setName(createMenu.getName());
                    menu.setBasePrice(createMenu.getBasePrice());
                    menu.setTax(createMenu.getTax());
                    menu.setSellingPrice(createMenu.getSellingPrice());
                    menu.setImage(createMenu.getImage());
                    menu.setDescription(createMenu.getDescription());
                    menu.setFoodType(createMenu.getFoodType());
                    menu.setCuisine(createMenu.getCuisine());
                    menu.setTags(createMenu.getTags());
                    menu.setBulkOnly(createMenu.getBulkOnly());
                    menu.setIsVegeterian(createMenu.getIsVegeterian());
                    menu.setCustomisations(createMenu.getCustomisations());
                    menu.setOpeningTime(createMenu.getOpeningTime());
                    menu.setClosingTime(createMenu.getClosingTime());
                    Menu m = menuRepo.saveAndFlush(menu);

                    MenuResponse menuResponse = new MenuResponse(m.getId(), m.getOutletId(), m.getName(), m.getDescription(),
                            m.getBasePrice(), m.getTax(), m.getSellingPrice(), m.getFoodType(), m.getCuisine(), m.getTags(),
                            m.getBulkOnly(), m.getIsVegeterian(), m.getImage(), m.getCustomisations(), m.getOpeningTime(),
                            m.getClosingTime(), m.getCreatedAt(), m.getUpdatedAt(), m.getActive());

                    return new ResponseEntity<>(new ResponseDTO<>("Success", null, menuResponse), HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>(new ResponseDTO<>("failure", "Incorrect selling price", null), HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>(new ResponseDTO<>("failure", "Incorrect tax value", null), HttpStatus.BAD_REQUEST);
            }

        }else {
            return new ResponseEntity<>(new ResponseDTO<>("failure", "Outlet is not Present", null), HttpStatus.BAD_REQUEST);
        }
    }
}
