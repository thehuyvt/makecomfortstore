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
    @JoinColumn(name = "cartId", nullable = false, referencedColumnName = "cartId")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "variantId", referencedColumnName = "variantId", nullable = false)
    private ProductVariantEntity productVariantEntity;
}
