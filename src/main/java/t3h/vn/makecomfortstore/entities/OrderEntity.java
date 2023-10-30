package t3h.vn.makecomfortstore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
@Data
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "phoneNumber", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "address", length = 250, nullable = false)
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntityList;
}
