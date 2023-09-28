package t3h.vn.makecomfortstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.makecomfortstore.entities.ProductSizeEntity;

import java.util.List;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Integer> {
    ProductSizeEntity findBySizeName(String name);

    @Query("SELECT ps FROM ProductSizeEntity ps WHERE ps.sizeStatus = 1")
    List<ProductSizeEntity> findListSize();
}
