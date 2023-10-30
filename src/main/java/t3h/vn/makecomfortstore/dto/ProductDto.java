package t3h.vn.makecomfortstore.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import t3h.vn.makecomfortstore.entities.ProductColorEntity;
import t3h.vn.makecomfortstore.entities.ProductSizeEntity;
import t3h.vn.makecomfortstore.entities.ProductVariantEntity;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class ProductDto {
    private Long productId;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    @Size(max = 150, message = "Tên sản phẩm không được quá 150 kí tự!")
    private String productName;

    @NotBlank(message = "Biệt hiệu không được bỏ trống!")
    @Size(min = 3, max = 20, message = "Biệt hiệu phải từ 3 - 20 kí tự!")
    private String productAlias;

    @Size(max = 1000, message = "Mô tả sản phẩm không được quá 1000 kí tự!")
    private String productDescription;

//    @NotNull(message = "Ảnh chính không được để trống!")
    private MultipartFile fileImage;
    private String mainImage;

//    @NotEmpty(message = "Ảnh phụ sản phẩm không được để trống!")
    private MultipartFile[] listFileExtraImage;
    private List<Long> listExtraImage;

    @NotNull(message = "Không được để trống loại sản phẩm")
    private Integer categoryId;

    @NotEmpty(message = "Size không được để trống!")
    private List<Integer> listSize;

    @NotEmpty(message = "Không được để trống màu sắc!")
    private List<Integer> listColor;

    @NotNull(message = "Không được bỏ trống giá sản phẩm!")
    @PositiveOrZero(message = "Giá sản phẩm không thể là giá trị âm!")
    private Double productPrice;

    @PositiveOrZero(message = "Giảm giá không thể là giá trị âm!")
    @Max(value = 100, message = "Giảm giá không được lớn hơn 100!")
    private Integer productDiscount;

    @NotNull(message = "Số lượng không được để trống!")
    @PositiveOrZero(message = "Số lượng không thể là giá trị âm!")
    private Integer productQuantity;

    private Integer productEnabled;

    private List<ProductSizeEntity> listProductSize;
    private List<ProductColorEntity> listProductColor;
    private List<ProductVariantEntity> listProductVariant;
}
