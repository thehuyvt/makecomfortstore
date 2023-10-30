package t3h.vn.makecomfortstore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "name")
    private String categoryName;

    @Column(name = "status")
    private Integer categoryStatus;

//    @Lob
//    @Column(name = "logo", columnDefinition = "MEDIUMBLOB")
//    private String categoryLogo;

    @Column(name = "logo")
    private String categoryLogo;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntityList;
}
