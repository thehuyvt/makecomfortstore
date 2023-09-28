package t3h.vn.makecomfortstore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "email", length = 150, unique = true)
    private String email;

    @Column(name = "phone", length = 20, unique = true)
    private String phone;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CartEntity> cartEntityList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntityList;
}
