package t3h.vn.makecomfortstore.entities;

import lombok.Data;
import org.hibernate.mapping.Join;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
@Data
public class ProductImageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageId;

    @Column(name = "subImage")
    private String subImage;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private ProductEntity product;
}
