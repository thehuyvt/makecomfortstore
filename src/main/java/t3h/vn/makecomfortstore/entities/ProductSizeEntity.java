package t3h.vn.makecomfortstore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_size")
@Data
public class ProductSizeEntity {
    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sizeId;

    @Column(name = "size_name")
    private String sizeName;

    @Column(name = "size_status")
    private Integer sizeStatus;

    @OneToMany(mappedBy = "productSizeEntity", cascade = CascadeType.ALL)
    private List<ProductVariantEntity> productVariantEntityList;
}
