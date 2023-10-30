package t3h.vn.makecomfortstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.makecomfortstore.entities.ProductColorEntity;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColorEntity, Integer> {
    @Query("SELECT pc FROM ProductColorEntity pc WHERE pc.colorStatus = 1 ORDER BY pc.colorId ASC")
    List<ProductColorEntity> findListProductColor();

    ProductColorEntity findByColorName(String name);

    ProductColorEntity findByColorId(Integer id);

}
