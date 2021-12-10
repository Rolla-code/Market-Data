package group14.orderservice.service;

import group14.orderservice.model.EOrder;
import group14.orderservice.repository.ProductOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;


    public EOrder saveOrder(EOrder order) {

        return productOrderRepository.save(order);
    }


    public List<EOrder> findAllOrders(){
        return productOrderRepository.findAll();
    }

    public List<EOrder> getOrderById(Long id){
        return productOrderRepository.findOrderByUserId(id);
    }

    public EOrder updateOrder(EOrder validatedOrder){
        return productOrderRepository.save(validatedOrder);
    }


    public void deleteOrderById(Long orderID){
        productOrderRepository.deleteOrderByOrderId(orderID);
    }

}
