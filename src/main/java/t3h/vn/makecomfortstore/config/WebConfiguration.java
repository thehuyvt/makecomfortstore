package t3h.vn.makecomfortstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
    @Value("${folder.image}")
    String folderImage;

    // Cấu hình xử lý tài nguyên tĩnh(css, js, image, ..) trong ứng dụng.
    // Nó đặt đường dẫn tới thư mục chứa hình ảnh và định danh 1 đường dẫn ảo('/static-image/**
    //Từ đó sử dụng nó để truy cập các tài nguyen tĩnh qua URL này.
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/static-image/**")
                .addResourceLocations("file:/" + folderImage);
    }
}
