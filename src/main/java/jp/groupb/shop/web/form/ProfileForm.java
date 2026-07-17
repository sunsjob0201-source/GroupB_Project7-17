package jp.groupb.shop.web.form;
import jakarta.validation.constraints.*;
import jp.groupb.shop.model.PaymentMethod;
import lombok.*;
import java.time.LocalDate;
@Getter @Setter
public class ProfileForm {
 @Size(min=8,max=72,message="変更する場合は8文字以上") private String password;
 @NotBlank @Size(max=80) private String name;
 @NotBlank @Pattern(regexp="\\d{3}-?\\d{4}",message="例: 100-0001") private String postalCode;
 @NotBlank @Size(max=200) private String address;
 @NotBlank @Pattern(regexp="[0-9-]{10,13}") private String phone;
 @NotNull @Past private LocalDate birthDate;
 @NotBlank @Email private String email;
 @NotNull private PaymentMethod paymentMethod;
}

