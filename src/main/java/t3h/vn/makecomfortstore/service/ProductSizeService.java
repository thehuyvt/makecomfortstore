package t3h.vn.makecomfortstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import t3h.vn.makecomfortstore.dto.ProductSizeDto;
import t3h.vn.makecomfortstore.entities.ProductSizeEntity;
import t3h.vn.makecomfortstore.repository.ProductSizeRepository;
import t3h.vn.makecomfortstore.response.ResponseObject;

import java.util.List;

@Service
public class ProductSizeService {
    @Autowired
    ProductSizeRepository productSizeRepository;

    public ResponseEntity<ResponseObject> save(ProductSizeDto productSizeDto){
        ProductSizeEntity productSizeEntity = new ProductSizeEntity();
        if(productSizeRepository.findBySizeName(productSizeDto.getSizeName()) == null){
            productSizeEntity.setSizeId(productSizeDto.getSizeId());
            productSizeEntity.setSizeName(productSizeDto.getSizeName());
            productSizeEntity.setSizeStatus(1);
            productSizeRepository.save(productSizeEntity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Tạo mới size thành công!", productSizeEntity)
            );
        }else{
            if (productSizeRepository.findBySizeName(productSizeDto.getSizeName()).getSizeStatus() == 0){
                productSizeEntity.setSizeId(productSizeDto.getSizeId());
                productSizeEntity.setSizeName(productSizeDto.getSizeName());
                productSizeEntity.setSizeStatus(1);
                productSizeRepository.save(productSizeEntity);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Tạo mới size thành công!", productSizeEntity)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Tên kích thước không được trùng lặp!", productSizeEntity)
            );
        }
    }

    //Edit save
    public ResponseEntity<ResponseObject> editSave(ProductSizeDto productSizeDto){
        ProductSizeEntity productSizeEntity = productSizeRepository.findById(productSizeDto.getSizeId()).orElse(null);

        if (productSizeEntity != null){
            if (productSizeEntity.getSizeName().equals(productSizeDto.getSizeName())
                    || productSizeRepository.findBySizeName(productSizeDto.getSizeName()) == null){
                productSizeEntity.setSizeName(productSizeDto.getSizeName());
                productSizeRepository.save(productSizeEntity);
                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Cập nhật size thành công!", productSizeEntity)
            );
            }else{
                if (productSizeRepository.findBySizeName(productSizeDto.getSizeName()).getSizeStatus() == 0){
                    productSizeEntity.setSizeName(productSizeDto.getSizeName());
                    productSizeRepository.save(productSizeEntity);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("success", "Cập nhật size thành công!", productSizeEntity)
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", "Tên kích cỡ không được trùng lặp với các kích cỡ khác", null)
                );
            }
        }else{
//            if (productSizeRepository.findBySizeName(productSizeDto.getSizeName()) == null){
//                productSizeEntity.setSizeId(productSizeDto.getSizeId());
//                productSizeEntity.setSizeName(productSizeDto.getSizeName());
//                productSizeRepository.save(productSizeEntity);
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("success", "Cập nhật size thành công!", productSizeEntity)
//                );
//            }else{
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", "Không có kích có id = " + productSizeDto.getSizeId(), null)
                );
//            }
        }
    }

    public ResponseEntity<ResponseObject> getSizeById(Integer id){
        ProductSizeEntity productSizeEntity = productSizeRepository.findById(id).orElse(null);
        if(productSizeEntity == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Không tìm thấy size có id là " + id, null)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Lấy size thành công!", productSizeEntity)
            );
        }
    }

    //lay danh sach size
    public ResponseEntity<ResponseObject> getSizeList(){
        List<ProductSizeEntity> productSizeEntityList = productSizeRepository.findListSize();
        if (productSizeEntityList != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Lấy danh sách kích cỡ thành công!", productSizeEntityList)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Danh sách rỗng!", null)
            );
        }
    }

    //Xóa kích cỡ
    public ResponseEntity<ResponseObject> deleteSize(Integer id){
        ProductSizeEntity productSizeEntity = productSizeRepository.findById(id).orElse(null);
        productSizeEntity.setSizeStatus(0);
        productSizeRepository.save(productSizeEntity);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Xóa kích cỡ thành công!", productSizeEntity)
        );
    }
}
