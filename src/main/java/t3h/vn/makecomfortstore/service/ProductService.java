package t3h.vn.makecomfortstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import t3h.vn.makecomfortstore.dto.ProductDto;
import t3h.vn.makecomfortstore.entities.*;
import t3h.vn.makecomfortstore.repository.*;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.utils.FileUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductService {
    @Value("${folder.image}")
    String urlImage;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductVariantRepository productVariantRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    public ResponseObject saveProduct(ProductDto productDto){
        ProductEntity productEntity = new ProductEntity();
        if (productRepository.findByProductAlias(productDto.getProductAlias()) != null){
            return new ResponseObject("failed", "Mã sản phẩm không được trùng lặp!", null);
        }

        productEntity.setProductName(productDto.getProductName());
        productEntity.setProductAlias(productDto.getProductAlias());
        productEntity.setProductDescription(productDto.getProductDescription());
        if(productDto.getFileImage() != null && !productDto.getFileImage().getOriginalFilename().equals("")){
            try {
                productDto.setMainImage(fileUtils.saveFile(productDto.getFileImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return new ResponseObject("failed", "Ảnh chính không được để trống!", null);
        }
        productEntity.setMainImage(productDto.getMainImage());
        productEntity.setCategory(categoryRepository.findByCategoryId(productDto.getCategoryId()));
        productEntity.setProductPrice(productDto.getProductPrice());
        productEntity.setProductDiscount(productDto.getProductDiscount());
        productEntity.setProductEnabled(1);
        //Time
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = dateTime.format(dateTimeFormatter);
        productEntity.setCreateTime(dateTimeNow);
        productEntity.setUpdateTime(dateTimeNow);
        productRepository.save(productEntity);

        //Product Image
        if (productDto.getListFileExtraImage().length > 0){
            for (MultipartFile extraImage : productDto.getListFileExtraImage()) {
                if (extraImage != null && !extraImage.getOriginalFilename().equals("")){
                    ProductImageEntity productImageEntity = new ProductImageEntity();
                    try {
                        productImageEntity.setSubImage(fileUtils.saveFile(extraImage));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    productImageEntity.setProduct(productEntity);
                    productImageRepository.save(productImageEntity);
                }
            }
        }
//        else{
//            return new ResponseObject("failed", "Ảnh phụ sản phẩm phải ít nhất 1 cái!", null);
//        }

        //productVariant
        for (Integer sizeId : productDto.getListSize()) {
            for (Integer colorId : productDto.getListColor()) {
                ProductVariantEntity productVariantEntity = new ProductVariantEntity();
                productVariantEntity.setInStock(productDto.getProductQuantity());
                productVariantEntity.setProductColorEntity(productColorRepository.findByColorId(sizeId));
                productVariantEntity.setProductSizeEntity(productSizeRepository.findBySizeId(colorId));
                productVariantEntity.setProduct(productEntity);
                productVariantRepository.save(productVariantEntity);
            }
        }

        return new ResponseObject("success", "Thêm mới sản phẩm thành công!", productDto);
    }

    //Lấy danh sách sản phẩm đang kích hoạt
    public ResponseObject listProduct(){
        List<ProductEntity> listProduct = productRepository.findListProduct();
        return new ResponseObject("success", "Lấy danh sách product thành công!", listProduct);
    }

    //Xóa(mềm) sản phẩm
    public ResponseObject deleteProduct(Long id){
        ProductEntity productEntity = productRepository.findByProductId(id);
        if (productEntity == null){
            return new ResponseObject("failed", "Sản phẩm không tồn tại!", null);
        }else{
            productEntity.setProductEnabled(0);
            productRepository.save(productEntity);
            return new ResponseObject("success", "Xóa sản phẩm thành công!", null);
        }
    }

    //Sửa sản phẩm
    //Lấy sản phẩm theo id
    public ResponseObject getProductById(Long id){
        ProductEntity productEntity = productRepository.findByProductId(id);
        ProductDto productDto = new ProductDto();
        if (productEntity == null || productEntity.getProductEnabled()==0){
            return new ResponseObject("failed", "Sản phẩm không tồn tại!", null);
        }else{
            List<ProductImageEntity> productImageEntityList = productImageRepository.findAllByProduct(productEntity);
            //danh sach kich co quan ao
            List<ProductSizeEntity> productSizeList = productSizeRepository.findListSize();
            List<ProductSizeEntity> productSizeExist = productVariantRepository.findListProductSizeByProduct(productEntity);
            productSizeList.removeAll(productSizeExist);

            //danh sach mau quan ao
            List<ProductColorEntity> productColorList = productColorRepository.findListProductColor();
            List<ProductColorEntity> productColorExist = productVariantRepository.findListProductColorByProduct(productEntity);
            productColorList.removeAll(productColorExist);

            //danh sach bien the san pham
            List<ProductVariantEntity> productVariantList = productVariantRepository.findListVariantByProduct(productEntity);

            productDto.setProductId(productEntity.getProductId());
            productDto.setProductName(productEntity.getProductName());
            productDto.setProductAlias(productEntity.getProductAlias());
            productDto.setProductDescription(productEntity.getProductDescription());
            productDto.setMainImage(productEntity.getMainImage());
            productDto.setProductPrice(productEntity.getProductPrice());
            productDto.setProductDiscount(productEntity.getProductDiscount());
            productDto.setCategoryId(productEntity.getCategory().getCategoryId());

            productDto.setListProductSize(productSizeList);
            productDto.setListProductColor(productColorList);

            productDto.setListProductVariant(productVariantList);
            return new ResponseObject("success", "Lấy sản phẩm thành công!", productDto);
        }
    }

    //Lưu cập nhật của sản phẩm
//    public ResponseObject saveUpdateProduct(ProductDto productDto){
//
//    }


}
