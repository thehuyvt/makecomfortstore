package t3h.vn.makecomfortstore.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_variants")
@Data
public class ProductVariantEntity {
    @Id
    @Column(name = "variant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantId;

    @Column(name = "in_stock")
    private Integer inStock;

    @OneToMany(mappedBy = "productVariantEntity", cascade = CascadeType.ALL)
    private List<CartDetailEntity> cartDetailEntityList;

    @OneToMany(mappedBy = "productVariantEntity", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntityList;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "size_id")
    private ProductSizeEntity productSizeEntity;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    private ProductColorEntity productColorEntity;
}
