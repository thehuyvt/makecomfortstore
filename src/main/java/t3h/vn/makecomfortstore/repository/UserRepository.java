package t3h.vn.makecomfortstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t3h.vn.makecomfortstore.entities.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
     UserEntity findByEmail(String email);
     UserEntity findByPhone(String phone);
}
