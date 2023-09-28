package t3h.vn.makecomfortstore.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {
    private Integer categoryId;

    @NotBlank(message = "Tên không được để trống!")
    @Size(max = 50, message = "Tên loại sản phẩm không được quá 50 kí tự!")
    private String categoryName;

    private String categoryLogo;

    private MultipartFile fileName;
}
