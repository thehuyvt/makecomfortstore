package t3h.vn.makecomfortstore.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "Email không được để trống!")
    @Email(message = "Chưa đúng định dạng email!")
    private String email;

    @NotBlank(message = "Không được để trống tên!")
    @Size(max = 50, min = 1)
    private String userName;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Size(min = 9, max = 20)
    private String phone;

    @Size(max = 200, message = "Địa chỉ không quá 200 kí tự!")
    private String address;

    @NotBlank(message = "Mật khẩu không được để trống!")
    @Size(min = 6, max = 16, message = "Mật khẩu phải trong khoảng 6 - 16 kí tự!")
    private String password;

    private String rePassword;

    private String role;

    private Integer status;
}
