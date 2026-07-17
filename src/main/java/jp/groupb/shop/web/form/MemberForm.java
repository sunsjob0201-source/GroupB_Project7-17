package jp.groupb.shop.web.form;

import jakarta.validation.constraints.*;
import jp.groupb.shop.model.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter
public class MemberForm {
 @NotBlank @Pattern(regexp="[A-Za-z0-9_-]{4,20}", message="4〜20文字の半角英数字で入力してください") private String memberId;
 @NotBlank @Size(min=8,max=72,message="8文字以上で入力してください") private String password;
 @NotBlank @Size(min=8,max=72,message="8文字以上で入力してください") private String passwordConfirm;
 @NotBlank @Size(max=50) private String name;
 @NotBlank @Pattern(regexp="\\d{3}-?\\d{4}", message="例: 100-0001") private String postalCode;
 @NotBlank @Size(max=200) private String address;
 @NotBlank @Pattern(regexp="[0-9-]{10,13}", message="電話番号の形式が正しくありません") private String phone;
 @NotNull @Past private LocalDate birthDate;
 @NotBlank @Email private String email;
 @NotNull private PaymentMethod paymentMethod;
}
