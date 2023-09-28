package t3h.vn.makecomfortstore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name")
    private String productName;

    @Column(name = "alias")
    private String productAlias;

    @Column(name = "mainImage")
    private String mainImage;

    @Column(name = "price")
    private Double productPrice;

    @Column(name = "discount")
    private Integer productDiscount;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "delete")
    private Integer productDelete;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageEntity> productImageEntityList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariantEntity> productVariantEntityList;

}
