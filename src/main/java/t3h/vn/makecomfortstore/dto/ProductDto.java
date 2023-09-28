package t3h.vn.makecomfortstore.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductDto {
    private Long productId;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    @Size(max = 150, message = "Tên sản phẩm không được quá 150 kí tự!")
    private String productName;

    @NotBlank(message = "Biệt hiệu không được bỏ trống!")
    @Size(min = 3, max = 20, message = "Biệt hiệu phải từ 3 - 20 kí tự!")
    private String productAlias;

    private String mainImage;
    private MultipartFile fileImage;

    @NotBlank(message = "Không được bỏ trống giá sản phẩm!")
    private Double productPrice;

    private Integer productDiscount;

    @Size(max = 1000, message = "Mô tả sản phẩm không được quá 1000 kí tự!")
    private String productDescription;

    private Integer productDelete;

    @NotBlank(message = "Không được để trống loại sản phẩm")
    private Integer categoryId;
}
