package t3h.vn.makecomfortstore.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductImageDto {
    private Long productImageId;
    private String subImage;
    private MultipartFile subImageFile;
    private Long productId;
}
