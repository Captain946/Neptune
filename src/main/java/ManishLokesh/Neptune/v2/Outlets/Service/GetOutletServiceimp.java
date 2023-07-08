package ManishLokesh.Neptune.v2.Outlets.Service;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Outlets.Entity.Outlet;
import ManishLokesh.Neptune.v2.Outlets.Repository.GetOutletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOutletServiceimp implements GetOutletService{

    @Autowired
    public GetOutletRepo outletRepo;

    @Override
    public ResponseEntity<ResponseDTO> GetOutletAll() {

        List<Outlet> outletList = outletRepo.findByStationCode(stationCode);
        return new ResponseEntity<>(
                new ResponseDTO("success",null,outletRepo.findAll()), HttpStatus.OK);
    }
}
