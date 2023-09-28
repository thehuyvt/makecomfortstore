package t3h.vn.makecomfortstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductColorDto {
    private Integer colorId;

    @NotBlank(message = "Tên màu sắc không được bỏ trống")
    @Size(min = 2, max = 50, message = "Tên màu sắc phải từ 2 - 50 kí tự!")
    private String colorName;

    private Integer colorStatus;
}
