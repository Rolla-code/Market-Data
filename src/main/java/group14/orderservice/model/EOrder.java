package group14.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EOrder {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long orderId;
    private String product;
    private long quantity;
    private double price;
    private Side side;
    private Long userId;
    @Column(name = "`timestamp`")
    @CreationTimestamp
    private Date timestamp;

    public EOrder(String product, long quantity, double price, Side side) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }
}
