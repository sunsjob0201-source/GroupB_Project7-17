package jp.groupb.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name="order_items") @Getter @Setter @NoArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="order_id") private ShopOrder order;
    @Column(nullable=false) private Long productId;
    @Column(nullable=false, length=100) private String productName;
    @Column(nullable=false, precision=12, scale=0) private BigDecimal unitPrice;
    @Column(nullable=false) private int quantity;
    public BigDecimal getSubtotal() { return unitPrice.multiply(BigDecimal.valueOf(quantity)); }
}

