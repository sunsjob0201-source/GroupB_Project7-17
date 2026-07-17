package jp.groupb.shop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="cart_items", uniqueConstraints=@UniqueConstraint(columnNames={"member_id","product_id"}))
@Getter @Setter @NoArgsConstructor
public class CartItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="member_id") private Member member;
    @ManyToOne(optional=false) @JoinColumn(name="product_id") private Product product;
    @Column(nullable=false) private int quantity;
}

