package jp.groupb.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity @Table(name="orders") @Getter @Setter @NoArgsConstructor
public class ShopOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="member_id") private Member member;
    @Column(nullable=false) private LocalDateTime orderedAt;
    @Column(nullable=false, length=80) private String customerName;
    @Column(nullable=false, length=8) private String postalCode;
    @Column(nullable=false, length=200) private String address;
    @Column(nullable=false, length=20) private String phone;
    @Column(nullable=false, length=150) private String email;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=30) private PaymentMethod paymentMethod;
    @Column(nullable=false, precision=12, scale=0) private BigDecimal totalAmount;
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true) @OrderBy("id") private List<OrderItem> items = new ArrayList<>();
    public void addItem(OrderItem item) { items.add(item); item.setOrder(this); }
}

