package group14.orderservice.repository;

import group14.orderservice.model.EOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository <EOrder, Long> {

    List<EOrder> findOrderByUserId(Long orderId);
    void deleteOrderByOrderId(Long id);

}
