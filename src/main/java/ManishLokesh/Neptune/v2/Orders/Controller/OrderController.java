package ManishLokesh.Neptune.v2.Orders.Controller;


import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class OrderController {

    @Autowired
    public OrderService service;

    @Autowired
    public JwtUtil jwtUtil;

    @PostMapping("/api/v2/create/order")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderRequestBody orderRequestBody, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(jwtUtil.validateToken(token)){
                return this.service.addOrder(orderRequestBody);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }
}
