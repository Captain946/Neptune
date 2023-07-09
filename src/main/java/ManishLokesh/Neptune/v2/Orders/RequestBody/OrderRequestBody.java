package ManishLokesh.Neptune.v2.Orders.RequestBody;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Orders.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderRequestBody implements OrderService {

    @Override
    public ResponseEntity<ResponseDTO> addOrder(OrderRequestBody orderRequestBody) {
        return new ResponseEntity<>(
                new ResponseDTO("success", null, "Order Created successfully"),
                HttpStatus.CREATED);
    }
}
