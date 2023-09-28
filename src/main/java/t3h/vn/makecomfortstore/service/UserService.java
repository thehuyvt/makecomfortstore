package t3h.vn.makecomfortstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import t3h.vn.makecomfortstore.dto.UserDto;
import t3h.vn.makecomfortstore.entities.UserEntity;
import t3h.vn.makecomfortstore.repository.UserRepository;
import t3h.vn.makecomfortstore.response.ResponseObject;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<ResponseObject> saveUser(UserDto userDto){
        if (userRepository.findByEmail(userDto.getEmail())!=null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Email này đã được sử dụng!", ""));
        }
        if (!(userDto.getPassword().equals(userDto.getRePassword()))){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Mật khẩu không trùng khớp!", ""));
        }
        if (userRepository.findByPhone(userDto.getPhone()) != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Số điện thoại này đã được sử dụng!", ""));
        }else{
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userDto.getUserName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userEntity.setPhone(userDto.getPhone());
            userEntity.setAddress(userDto.getAddress());
            userEntity.setRole("USER");
            if(userDto.getStatus() != null) {
                userEntity.setStatus(userDto.getStatus());
            }else{
                userEntity.setStatus(0);
            }
            userRepository.save(userEntity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Đăng kí thành công!", userEntity));
        }
    }

    public ResponseEntity<ResponseObject> getListUser(){
        List<UserEntity> userEntityList = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Lấy danh sách người dùng thành công", userEntityList)
        );
    }

    public ResponseEntity<ResponseObject> deleteUser(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity != null){
            userEntity.setStatus(2);
            userRepository.save(userEntity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Bạn đã xóa tài khoản thành công!", userEntity)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Xóa tài khoản thất bại!", null)
            );
        }
    }

//    Lấy user bằng id
    public ResponseEntity<ResponseObject> getUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy tài khoản có id = " + id, null)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Lấy thông tin tài khoản thành công!", userEntity)
            );
        }
    }

    public ResponseEntity<ResponseObject> saveEditUser(UserDto userDto){
        UserEntity userEntity = userRepository.findById(userDto.getId()).orElse(null);
        if (userEntity != null){
            if (!userDto.getEmail().equals(userEntity.getEmail()) && userRepository.findByEmail(userDto.getEmail()) != null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", "Email này đã được sử dụng!", null)
                );
            }
            if(!userDto.getPhone().equals(userEntity.getPhone()) && userRepository.findByPhone(userDto.getPhone()) != null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", "Số điện thoại này đã được sử dụng!", null)
                );
            }
            userEntity.setUserName(userDto.getUserName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setAddress(userDto.getAddress());
            userEntity.setPhone(userDto.getPhone());
            userEntity.setStatus(userDto.getStatus());
            userRepository.save(userEntity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Sửa tài khoản thành công!", userEntity)
            );
        }else {

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Không có tài khoản nào có id = " + userDto.getId(), null)
            );
        }
    }
}
