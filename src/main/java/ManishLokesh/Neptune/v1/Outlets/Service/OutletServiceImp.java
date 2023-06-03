package ManishLokesh.Neptune.v1.Outlets.Service;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.v1.Outlets.Entity.Outlet;
import ManishLokesh.Neptune.v1.Outlets.ReponseBody.CreateOutletResponse;
import ManishLokesh.Neptune.v1.Outlets.Repository.OutletRepo;
import ManishLokesh.Neptune.v1.Outlets.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service 
public class OutletServiceImp implements OutletService{

    private Logger logger = LoggerFactory.getLogger("app.OutletService");
    private final OutletRepo outletRepo;
    private final JwtUtil jwtUtil;
    @Autowired
    public OutletServiceImp(OutletRepo outletRepo, JwtUtil jwtUtil) {
        this.outletRepo = outletRepo;
        this.jwtUtil = jwtUtil;
    }



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
        Outlet o = outletRepo.saveAndFlush(outlet);

        CreateOutletResponse createOutletResponse = new CreateOutletResponse(o.getId(),o.getOutletName(),
                o.getMinOrderValue(),o.getOrderTiming(),o.getOpeningTime(),o.getClosingTime(),
                o.getDeliveryCost(),o.getAddress(),o.getCity(),o.getState(),o.getPrepaid(),o.getCompanyName(),
                o.getPanCard(),o.getGstNo(),o.getFssaiNo(),o.getFssaiValidUpto(),o.getOutletClosedFrom(),
                o.getOutletClosedTo(),
                o.getActive(),o.getLogoImage(),o.getEmailId(),o.getMobileNo(),o.getStationCode());

        return new ResponseEntity<>(new ResponseDTO("success",null,createOutletResponse), HttpStatus.CREATED);
    }
}
