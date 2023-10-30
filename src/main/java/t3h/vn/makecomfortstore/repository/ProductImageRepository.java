package t3h.vn.makecomfortstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t3h.vn.makecomfortstore.entities.ProductEntity;
import t3h.vn.makecomfortstore.entities.ProductImageEntity;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
    List<ProductImageEntity> findAllByProduct(ProductEntity productEntity);

}
