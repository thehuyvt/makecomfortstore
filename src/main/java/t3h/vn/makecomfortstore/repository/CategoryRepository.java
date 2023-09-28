package t3h.vn.makecomfortstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.makecomfortstore.entities.CategoryEntity;

import java.util.List;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryName(String name);

    @Query("SELECT c FROM CategoryEntity c WHERE c.categoryStatus = 1 ORDER BY c.categoryId ASC")
    List<CategoryEntity> findListCategory();
}
