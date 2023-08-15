package ManishLokesh.Neptune.v1.OutletsAndMenu.Service;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import ManishLokesh.Neptune.v1.OutletsAndMenu.ReponseBody.CreateOutletResponse;
import ManishLokesh.Neptune.v1.OutletsAndMenu.ReponseBody.MenuResponse;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.MenuRepo;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.OutletRepo;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.lang.Long.valueOf;

@Service 
public class OutletServiceImp implements OutletService{

    private Logger logger = LoggerFactory.getLogger("app.OutletService");
    @Autowired
    public OutletRepo outletRepo;
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
        outlet.setActive(false);
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
    public ResponseEntity<ResponseDTO> UpdateOutlet(CreateOutlet updateOutlet,Long outletId) {
        Optional <Outlet> outletDetails = outletRepo.findById(outletId);
        if(outletDetails.isPresent()){
            Outlet outlet = outletDetails.get();
            outlet.setStationCode(updateOutlet.getStationCode());
            outlet.setUpdatedAt(LocalDateTime.now().toString());
            outlet.setOutletName(updateOutlet.getOutletName());
            outlet.setMinOrderValue(updateOutlet.getMinOrderValue());
            outlet.setOrderTiming(updateOutlet.getOrderTiming());
            outlet.setOpeningTime(updateOutlet.getOpeningTime());
            outlet.setClosingTime(updateOutlet.getClosingTime());
            outlet.setDeliveryCost(updateOutlet.getDeliveryCost());
            outlet.setAddress(updateOutlet.getAddress());
            outlet.setState(updateOutlet.getState());
            outlet.setCity(updateOutlet.getCity());
            outlet.setPrepaid(updateOutlet.getPrepaid());
            outlet.setCompanyName(updateOutlet.getCompanyName());
            outlet.setPanCard(updateOutlet.getPanCard());
            outlet.setGstNo(updateOutlet.getGstNo());
            outlet.setFssaiNo(updateOutlet.getFssaiNo());
            outlet.setFssaiValidUpto(updateOutlet.getFssaiValidUpto());
            outlet.setOutletClosedTo(updateOutlet.getOutletClosedTo());
            outlet.setOutletClosedFrom(updateOutlet.getOutletClosedFrom());
            outlet.setLogoImage(updateOutlet.getLogoImage());
            outlet.setEmailId(updateOutlet.getEmailId());
            outlet.setMobileNo(updateOutlet.getMobileNo());
            outletRepo.save(outlet);
            return new ResponseEntity<>(new ResponseDTO<>("success",null,outlet),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","outlet is not found",null),
                HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<ResponseDTO> CreateNewMenu(Long outletId,CreateMenu createMenu) {
        if(outletRepo.findById(outletId).isPresent()){
            if(((Float.parseFloat(createMenu.getBasePrice())) * 0.05) == Float.parseFloat(createMenu.getTax())){
                if(((Float.parseFloat(createMenu.getBasePrice()) + Float.parseFloat(createMenu.getTax()))
                        == Float.parseFloat(createMenu.getSellingPrice()))){
                    Menu menu = new Menu();
                    menu.setCreatedAt(LocalDateTime.now().toString());
                    menu.setActive(false);
                    menu.setOutletId(outletId.toString());
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

                    MenuResponse menuResponse = new MenuResponse(m.getId(), m.getName(), m.getDescription(),
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

    @Override
    public ResponseEntity<ResponseDTO> UpdateMenu(CreateMenu updateMenu, Long outletId, Long menuId) {
        Optional<Outlet> outletDetails = outletRepo.findById(outletId);
        if(outletDetails.isPresent()){
            Optional<Menu> menuDetails = menuRepo.findById(menuId);
            if(menuDetails.isPresent()){
                if((Float.parseFloat(updateMenu.getBasePrice()) * 0.05) == Float.parseFloat(updateMenu.getTax())){
                    if((Float.parseFloat(updateMenu.getBasePrice()) + Float.parseFloat(updateMenu.getTax())) == Float.parseFloat(updateMenu.getSellingPrice())){
                        Menu menu = menuDetails.get();
                        menu.setUpdatedAt(LocalDateTime.now().toString());
                        menu.setName(updateMenu.getName());
                        menu.setImage(updateMenu.getImage());
                        menu.setDescription(updateMenu.getDescription());
                        menu.setBasePrice(updateMenu.getBasePrice());
                        menu.setTax(updateMenu.getTax());
                        menu.setSellingPrice(updateMenu.getSellingPrice());
                        menu.setFoodType(updateMenu.getFoodType());
                        menu.setCuisine(updateMenu.getCuisine());
                        menu.setTags(updateMenu.getTags());
                        menu.setBulkOnly(updateMenu.getBulkOnly());
                        menu.setIsVegeterian(updateMenu.getIsVegeterian());
                        menu.setCustomisations(updateMenu.getCustomisations());
                        menu.setOpeningTime(updateMenu.getOpeningTime());
                        menu.setClosingTime(updateMenu.getClosingTime());
                        menuRepo.save(menu);
                        return new ResponseEntity<>(new ResponseDTO("success",null,menu),HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>(new ResponseDTO("failure","Incorrect Selling price",null), HttpStatus.BAD_REQUEST);
                    }
                }else{
                    return new ResponseEntity<>(new ResponseDTO("failure","Incorrect Tax value",null), HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>(new ResponseDTO("failure","Menu is not found",null), HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(new ResponseDTO("failure","Outlet is not found",null), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<ResponseDTO> ActiveOutlet(Long outletId,Boolean status) {
        Optional<Outlet> outletDetails = outletRepo.findById(outletId);
        if(outletDetails.isPresent()){
            Outlet outlet = outletDetails.get();
            outlet.setActive(status);
            outlet.setUpdatedAt(LocalDateTime.now().toString());
            outletRepo.save(outlet);
            return new ResponseEntity<>(new ResponseDTO<>("success"," Status updated",null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Outlet is not found",null),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDTO> ActiveMenu(Long menuId,Boolean status) {
        Optional<Menu> menuDetails = menuRepo.findById(menuId);
        if(menuDetails.isPresent()){
            Menu menu = menuDetails.get();
            menu.setActive(status);
            menu.setUpdatedAt(LocalDateTime.now().toString());
            menuRepo.save(menu);
            return new ResponseEntity<>(new ResponseDTO<>("success","Status updated",null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","menu is not found",null),
                HttpStatus.BAD_REQUEST);
    }
}
