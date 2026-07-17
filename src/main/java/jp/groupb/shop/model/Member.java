package jp.groupb.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table(name="members") @Getter @Setter @NoArgsConstructor
public class Member {
    @Id @Column(name="member_id", length=20) private String memberId;
    @Column(nullable=false) private String password;
    @Column(name="member_name", nullable=false, length=50) private String name;
    @Column(name="postal_code", nullable=false, length=8) private String postalCode;
    @Column(nullable=false, length=255) private String address;
    @Column(name="phone_number", nullable=false, length=20) private String phone;
    @Column(name="birth_date", nullable=false) private LocalDate birthDate;
    @Column(nullable=false, length=150) private String email;
    @Enumerated(EnumType.STRING) @Column(name="payment_method", nullable=false, length=20) private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=10) private Role role = Role.USER;
}
