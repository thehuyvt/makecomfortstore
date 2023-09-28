package t3h.vn.makecomfortstore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_variants")
@Data
public class ProductVariantEntity {
    @Id
    @Column(name = "variantId")
    private Long variantId;

    @Column(name = "instock")
    private Integer instock;

    @OneToMany(mappedBy = "productVariantEntity", cascade = CascadeType.ALL)
    private List<CartDetailEntity> cartDetailEntityList;

    @OneToMany(mappedBy = "productVariantEntity", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntityList;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "size_id")
    private ProductSizeEntity productSizeEntity;

    @ManyToOne
    @JoinColumn(name = "colorId", referencedColumnName = "colorId")
    private ProductColorEntity productColorEntity;
}
