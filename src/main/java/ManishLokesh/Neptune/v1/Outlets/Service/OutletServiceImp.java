package ManishLokesh.Neptune.v1.Outlets.Service;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.v1.Outlets.Entity.Menu;
import ManishLokesh.Neptune.v1.Outlets.Entity.Outlet;
import ManishLokesh.Neptune.v1.Outlets.ReponseBody.CreateOutletResponse;
import ManishLokesh.Neptune.v1.Outlets.ReponseBody.MenuResponse;
import ManishLokesh.Neptune.v1.Outlets.Repository.MenuRepo;
import ManishLokesh.Neptune.v1.Outlets.Repository.OutletRepo;
import ManishLokesh.Neptune.v1.Outlets.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.lang.Long.valueOf;

@Service 
public class OutletServiceImp implements OutletService{

    private Logger logger = LoggerFactory.getLogger("app.OutletService");
    public   OutletRepo outletRepo;
    @Autowired
    public MenuRepo menuRepo;



    @Override
    public ResponseEntity<ResponseDTO> CreateNewOutlet(CreateOutlet createOutlet) {

        logger.info("hlw working name is : {}", createOutlet.getOutletName());

        Outlet outlet = new Outlet();
        outlet.setCreatedAt(LocalDate.now().toString());
        outlet.setStationCode(createOutlet.getStationCode());
        outlet.setOutletName(createOutlet.getOutletName());
        outlet.setMinOrderValue(createOutlet.getMinOrderValue());
        outlet.setOrderTiming(createOutlet.getOrderTiming());
        outlet.setOpeningTime(createOutlet.getOpeningTime());
        outlet.setClosingTime(createOutlet.getClosingTime());
        outlet.setDeliveryCost(createOutlet.getDeliveryCost());
        outlet.setAddress(createOutlet.getAddress());
        outlet.setCity(createOutlet.getCity());
        outlet.setState(createOutlet.getState());
        outlet.setPrepaid(createOutlet.getPrepaid());
        outlet.setCompanyName(createOutlet.getCompanyName());
        outlet.setPanCard(createOutlet.getPanCard());
        outlet.setGstNo(createOutlet.getGstNo());
        outlet.setFssaiNo(createOutlet.getFssaiNo());
        outlet.setFssaiValidUpto(createOutlet.getFssaiValidUpto());
        outlet.setOutletClosedFrom(createOutlet.getOutletClosedFrom());
        outlet.setOutletClosedTo(createOutlet.getOutletClosedTo());
        outlet.setActive(createOutlet.getActive());
        outlet.setLogoImage(createOutlet.getLogoImage());
        outlet.setEmailId(createOutlet.getEmailId());
        outlet.setMobileNo(createOutlet.getMobileNo());
        outlet.setRatingValue(3.3);
        outlet.setCreatedAt(LocalDateTime.now().toString());
        Outlet o = outletRepo.saveAndFlush(outlet);

        CreateOutletResponse createOutletResponse = new CreateOutletResponse(o.getId(),o.getOutletName(),
                o.getMinOrderValue(),o.getOrderTiming(),o.getOpeningTime(),o.getClosingTime(),
                o.getDeliveryCost(),o.getAddress(),o.getCity(),o.getState(),o.getPrepaid(),o.getCompanyName(),
                o.getPanCard(),o.getGstNo(),o.getFssaiNo(),o.getFssaiValidUpto(),o.getOutletClosedFrom(),
                o.getOutletClosedTo(),
                o.getActive(),o.getCreatedAt(),o.getUpdatedAt(),o.getLogoImage(),o.getEmailId(),
                o.getMobileNo(),o.getStationCode());

        return new ResponseEntity<>(new ResponseDTO("success",null,createOutletResponse),
                HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<ResponseDTO> CreateNewMenu(CreateMenu createMenu) {
        if(outletRepo.findById(valueOf(createMenu.getOutletId())).isPresent()){
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
