package t3h.vn.makecomfortstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import t3h.vn.makecomfortstore.dto.CategoryDto;
import t3h.vn.makecomfortstore.entities.CategoryEntity;
import t3h.vn.makecomfortstore.repository.CategoryRepository;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.utils.FileUtils;

import java.util.List;

@Service
public class CategoryService {
    @Value("${folder.image}")
    String urlImage;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileUtils fileUtils;

    //lưu phân loại
    public ResponseObject save(CategoryDto categoryDto){
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryEntity categoryCheck = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if (categoryCheck == null || (categoryCheck != null && categoryCheck.getCategoryStatus() == 0) ){
            categoryEntity.setCategoryId(categoryDto.getCategoryId());
            categoryEntity.setCategoryName(categoryDto.getCategoryName());
            categoryEntity.setCategoryStatus(1);
            if (categoryDto.getFileName() != null && !categoryDto.getFileName().getOriginalFilename().equals("")){
                try {
                    categoryDto.setCategoryLogo(fileUtils.saveFile(categoryDto.getFileName()));
                } catch (Exception e) {
                }
            }
            categoryEntity.setCategoryLogo(categoryDto.getCategoryLogo());
            categoryRepository.save(categoryEntity);
            return new ResponseObject("success", "Tạo phân loại thành công!", categoryEntity);
        }else{
            return new ResponseObject("failed", "Tên phân loại không được trùng nhau!", null);
        }
    }

    //lấy dsach
    public ResponseObject getCategoryList(){
        List<CategoryEntity> categoryEntityList = categoryRepository.findListCategory();
        if (categoryEntityList == null){
            return new ResponseObject("not found", "Danh sách phân loại rỗng!", null);
        }else{
            return new ResponseObject("success", "Lấy danh sách phân loại thành công", categoryEntityList);
        }
    }

    //lay loại sản phẩm bằng id
    public CategoryEntity getCategoryById(Integer id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
       return categoryEntity;
    }

    // cập nhật phân loại
    public ResponseObject updateCategory(CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryRepository.findById(categoryDto.getCategoryId()).orElse(null);
        CategoryEntity categoryCheck = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if (categoryEntity.getCategoryName().equals(categoryDto.getCategoryName()) || categoryCheck == null ||
                (categoryCheck != null && categoryCheck.getCategoryStatus() == 0)){

            categoryEntity.setCategoryName(categoryDto.getCategoryName());
            if (categoryDto.getFileName() != null && !categoryDto.getFileName().getOriginalFilename().equals("")){
                try {
                    fileUtils.deleteFile(urlImage + categoryEntity.getCategoryLogo());
                    categoryDto.setCategoryLogo(fileUtils.saveFile(categoryDto.getFileName()));
                    categoryEntity.setCategoryLogo(categoryDto.getCategoryLogo());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            categoryRepository.save(categoryEntity);
            return new ResponseObject("success", "Sửa phân loại thành công!", categoryEntity);
        } else{
            return new ResponseObject("failed", "Tên phân loại không được trùng nhau!", null);
        }
    }

//    Xóa phân loại
    public ResponseObject deleteCategory(Integer id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if (categoryEntity != null){
            categoryEntity.setCategoryStatus(0);
            categoryRepository.save(categoryEntity);
            return new ResponseObject("success", "Xóa phân loại thành công!", null);
        }else{
            return new ResponseObject("not found", "Không tìm thấy phân loại có id = " + id, null);
        }
    }

}
