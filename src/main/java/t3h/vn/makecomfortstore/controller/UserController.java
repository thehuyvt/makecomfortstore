package t3h.vn.makecomfortstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.makecomfortstore.dto.UserDto;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/management/user")
public class UserController {
    @Autowired
    public UserService userService;

//    Lấy danh sách người dùng
    @GetMapping("/list")
    public String userList(Model model) {
        ResponseEntity<ResponseObject> response = userService.getListUser();
        model.addAttribute("users", response.getBody().getData());
//        model.addAttribute("notify", response.getBody().getMessage());
        return "admin/user/list-user.html";
    }

//    Xóa người dùng
    @RequestMapping(value = "delete-user/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model){
        ResponseEntity<ResponseObject> response = userService.deleteUser(id);
        redirectAttributes.addAttribute("message", "Xóa tài khoản thành công");
        model.addAttribute("notify", response.getBody().getMessage());

        return "redirect:/management/user/list";
    }

//    Thêm người dùng
    @GetMapping("add-user")
    public String createUser(Model model){
        model.addAttribute("userDto", new UserDto());
        return "admin/user/create-user.html";
    }

    @PostMapping("add-user/save")
    public String saveUser(@Valid UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            return "admin/user/create-user.html";
        }
        ResponseEntity<ResponseObject> response = userService.saveUser(userDto);
        if (response.getBody().getStatus().equals("success")){
            redirectAttributes.addAttribute("message", response.getBody().getMessage());
            return "redirect:/management/user/list";
        }else{
            model.addAttribute("error", response.getBody().getMessage());
            return "admin/user/create-user.html";
        }
    }

//    Sửa người dùng
    @GetMapping("edit-user/{id}")
    public String editUser(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        ResponseEntity<ResponseObject> response = userService.getUserById(id);
        if(response.getBody().getStatus().equals("failed")){
            redirectAttributes.addAttribute("message", response.getBody().getMessage());
            return "redirect:/management/user/list";
        }else{
            model.addAttribute("userDto", response.getBody().getData());
            return "admin/user/detail-user.html";
        }
    }

    @PostMapping(value = "edit-user/save/{id}")
    public String editUserSave(@Valid UserDto userDto, BindingResult bindingResult, Model model, @PathVariable Long id){
        userDto.setId(id);

        if (bindingResult.hasFieldErrors("userName") || bindingResult.hasFieldErrors("email")
                || bindingResult.hasFieldErrors("address") || bindingResult.hasFieldErrors("phone")){

            return "admin/user/detail-user.html";
        }

        ResponseEntity<ResponseObject> response = userService.saveEditUser(userDto);
        if(response.getBody().getStatus().equals("failed")){
            model.addAttribute("error", response.getBody().getMessage());
            return "admin/user/detail-user.html";
        }else {
            model.addAttribute("notify", response.getBody().getMessage());
            return "redirect:/management/user/list";
        }
    }
}
