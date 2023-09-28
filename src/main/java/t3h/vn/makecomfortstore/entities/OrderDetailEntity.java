package t3h.vn.makecomfortstore.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
@Data
public class OrderDetailEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "variantId", referencedColumnName = "variantId", nullable = false)
    private ProductVariantEntity productVariantEntity;
}
