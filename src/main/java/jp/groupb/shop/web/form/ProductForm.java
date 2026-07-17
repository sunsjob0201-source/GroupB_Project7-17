package jp.groupb.shop.web.form;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
@Getter @Setter
public class ProductForm {
 @NotBlank @Size(max=100) private String name;
 @NotBlank @Size(max=1000) private String description;
 @NotNull @DecimalMin(value="0") @Digits(integer=10,fraction=0) private BigDecimal price;
 @Min(0) private int stock;
 @Size(max=500) private String imageUrl;
}

