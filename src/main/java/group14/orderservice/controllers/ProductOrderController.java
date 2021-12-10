package group14.orderservice.controllers;
import group14.orderservice.model.EOrder;
import group14.orderservice.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductOrderController {
    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private RestTemplate restTemplate;

    //Constructor
    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    //Get all orders
    @GetMapping("/all")
    public ResponseEntity<List<EOrder>> findAllOrders (){
        List<EOrder> Order = productOrderService.findAllOrders();
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }

    //Add order
    @PostMapping("/create")
    public String addOrder (@RequestBody EOrder order){
        productOrderService.saveOrder(order);

        EOrder sendOrder = new EOrder(order.getProduct(),order.getQuantity(),order.getPrice(),order.getSide());
        String uri = "https://exchange.matraining.com/d6e17803-f416-4677-b3c4-17687fcfe6a3/order";

        String token = restTemplate.postForObject(uri,sendOrder, String.class);

        return token;
    }

    //Get order by id
    @GetMapping("/find/{id}")
    public List<EOrder> getOrderById (@PathVariable("id") Long userId){
        return productOrderService.getOrderById(userId);
    }

    //Update order
    @PutMapping("/update")
    public EOrder updateOrder(@RequestBody EOrder validatedOrder){
        return productOrderService.updateOrder(validatedOrder);
    }

    //Delete order
    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") Long orderID){
       productOrderService.deleteOrderById(orderID);
    }

}
