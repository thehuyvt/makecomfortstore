package t3h.vn.makecomfortstore.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart_detail")
@Data
public class CartDetailEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailId;

    @Column(name = "quantity")
    private int cartDetailQuantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false, referencedColumnName = "cart_id")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "variant_id", referencedColumnName = "variant_id", nullable = false)
    private ProductVariantEntity productVariantEntity;
}
