package t3h.vn.makecomfortstore.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductSizeDto {
    private Integer sizeId;

    @NotBlank(message = "Tên size không được để trống!")
    @Size(min = 1, max = 5, message = "Độ dài size từ 1 - 5 ký tự!")
    private String sizeName;

    private Integer sizeStatus;
}
