package ManishLokesh.Neptune.v2.Orders.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    public ResponseEntity<ResponseDTO> addOrder(OrderRequestBody orderRequestBody);

}
