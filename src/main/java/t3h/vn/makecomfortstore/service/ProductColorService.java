package t3h.vn.makecomfortstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import t3h.vn.makecomfortstore.dto.ProductColorDto;
import t3h.vn.makecomfortstore.entities.ProductColorEntity;
import t3h.vn.makecomfortstore.repository.ProductColorRepository;
import t3h.vn.makecomfortstore.response.ResponseObject;

import java.util.List;

@Service
public class ProductColorService {
    @Autowired
    ProductColorRepository colorRepository;
    //lay danh sach color
    public ResponseObject getListColor(){
        List<ProductColorEntity> listColor = colorRepository.findListProductColor();
        return new ResponseObject("success", "Lấy danh sách màu sắc thành công!", listColor);
    }

    //lay color bang id
    public ResponseObject getColorById(Integer id){
        ProductColorEntity productColorEntity = colorRepository.findById(id).orElse(null);
        if (productColorEntity == null){
            return new ResponseObject("failed", "Lấy màu sắc thành công!", null);
        }else{
            return new ResponseObject("success", "Lấy màu sắc thành công!", productColorEntity);
        }
    }

    //lưu màu
    public ResponseObject save(ProductColorDto productColorDto){
        ProductColorEntity productColorEntity = new ProductColorEntity();
        if (colorRepository.findByColorName(productColorDto.getColorName()) == null){
            productColorEntity.setColorId(productColorDto.getColorId());
            productColorEntity.setColorName(productColorDto.getColorName());
            productColorEntity.setColorStatus(1);
            colorRepository.save(productColorEntity);
            return new ResponseObject("success", "Thêm màu sắc thành công!", productColorEntity);
        }else{
            if (colorRepository.findByColorName(productColorDto.getColorName()).getColorStatus() == 0){
                productColorEntity.setColorId(productColorDto.getColorId());
                productColorEntity.setColorName(productColorDto.getColorName());
                productColorEntity.setColorStatus(1);
                colorRepository.save(productColorEntity);
                return new ResponseObject("success", "Thêm màu sắc thành công!", productColorEntity);
            }else{
                return new ResponseObject("failed", "Tên màu sắc không được trùng lặp!", null);
            }
        }
    }

    //edit color
    public ResponseObject editColor(ProductColorDto productColorDto){
        ProductColorEntity productColorEntity = colorRepository.findById(productColorDto.getColorId()).orElse(null);
        if (productColorEntity != null){
            if (colorRepository.findByColorName(productColorDto.getColorName()) == null
                    || colorRepository.findByColorName(productColorDto.getColorName()).getColorStatus() == 0){
                productColorEntity.setColorName(productColorDto.getColorName());
                colorRepository.save(productColorEntity);
                return new ResponseObject("success", "Cập nhật màu sắc thành công!", productColorEntity);
            }else{
                return new ResponseObject("failed", "Tên màu sắc không được trùng lặp!", null);
            }
        }else{
            return new ResponseObject("not found", "Màu sắc không tồn tại!", null);
        }
    }

    //delete color
    public ResponseObject deleteColor(Integer id){
        ProductColorEntity productColorEntity = colorRepository.findById(id).orElse(null);
        if (productColorEntity != null){
            productColorEntity.setColorStatus(0);
            colorRepository.save(productColorEntity);
            return new ResponseObject("success", "Xóa màu sắc thành công!", null);
        }else{
            return new ResponseObject("not found", "Màu sắc không tồn tại!", null);
        }
    }

}
