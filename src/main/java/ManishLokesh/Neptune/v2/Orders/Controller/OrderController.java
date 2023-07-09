package ManishLokesh.Neptune.v2.Orders.Controller;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {

    @Autowired
    public OrderService service;

    @PostMapping("/api/v2/create/order")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderRequestBody orderRequestBody){
        return this.service.addOrder(orderRequestBody);
    }
}
