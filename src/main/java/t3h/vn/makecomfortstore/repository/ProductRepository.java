package t3h.vn.makecomfortstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.makecomfortstore.entities.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByProductAlias(String alias);

    ProductEntity findByProductId(Long id);

    @Query("SELECT p FROM ProductEntity p WHERE p.productEnabled = 1")
    List<ProductEntity> findListProduct();

}
