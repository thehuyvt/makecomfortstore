package t3h.vn.makecomfortstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import t3h.vn.makecomfortstore.entities.UserEntity;
import t3h.vn.makecomfortstore.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

public class UserAccountService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserAccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException("Không tồn tại tài khoản!");

//        return new UserAccountDetails(userEntity);
        return new User(email, userEntity.getPassword(), userEntity.getStatus() == 1,
                true, true, true,
                Arrays.asList(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority("ROLE_" + userEntity.getRole())}));
    }

//    public UserDetails loadUserByEmail(String email){
//        Optional<UserEntity> userDetail = userRepository.findByEmail(email);
//        return us
//    }
}
