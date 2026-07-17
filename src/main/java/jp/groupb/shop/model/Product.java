package jp.groupb.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name="products") @Getter @Setter @NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=100) private String name;
    @Column(nullable=false, length=1000) private String description;
    @Column(nullable=false, precision=12, scale=0) private BigDecimal price;
    @Column(nullable=false) private int stock;
    @Column(length=500) private String imageUrl;
}

