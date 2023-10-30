package t3h.vn.makecomfortstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import t3h.vn.makecomfortstore.entities.ProductColorEntity;
import t3h.vn.makecomfortstore.entities.ProductEntity;
import t3h.vn.makecomfortstore.entities.ProductSizeEntity;
import t3h.vn.makecomfortstore.entities.ProductVariantEntity;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariantEntity, Long> {
    @Query("SELECT v.productSizeEntity FROM ProductVariantEntity v WHERE v.product = :productEntity")
    List<ProductSizeEntity> findListProductSizeByProduct(@Param("productEntity") ProductEntity productEntity);


    @Query("SELECT v.productColorEntity FROM ProductVariantEntity  v WHERE v.product = :productEntity")
    List<ProductColorEntity> findListProductColorByProduct(@Param("productEntity") ProductEntity productEntity);

    @Query("SELECT v FROM ProductVariantEntity v WHERE v.product = :productEntity")
    List<ProductVariantEntity> findListVariantByProduct(@Param("productEntity") ProductEntity productEntity);
}
