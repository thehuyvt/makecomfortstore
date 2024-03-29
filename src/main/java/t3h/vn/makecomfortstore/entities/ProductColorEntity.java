package t3h.vn.makecomfortstore.entities;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_color")
@Data
public class ProductColorEntity {
    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer colorId;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "color_status")
    private Integer colorStatus;

    @OneToMany(mappedBy = "productColorEntity", cascade = CascadeType.ALL)
    private List<ProductVariantEntity> productVariantEntityList;
}
