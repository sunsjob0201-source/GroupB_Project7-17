package jp.groupb.shop.web.form;
import jakarta.validation.constraints.*;
import jp.groupb.shop.model.PaymentMethod;
import lombok.*;
@Getter @Setter
public class CheckoutForm {
 @NotBlank @Size(max=80) private String customerName;
 @NotBlank @Pattern(regexp="\\d{3}-?\\d{4}",message="例: 100-0001") private String postalCode;
 @NotBlank @Size(max=200) private String address;
 @NotBlank @Pattern(regexp="[0-9-]{10,13}") private String phone;
 @NotBlank @Email private String email;
 @NotNull private PaymentMethod paymentMethod;
}

