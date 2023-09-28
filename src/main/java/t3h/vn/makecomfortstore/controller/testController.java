package t3h.vn.makecomfortstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import t3h.vn.makecomfortstore.entities.UserEntity;
import t3h.vn.makecomfortstore.repository.UserRepository;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "")
public class testController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

//    @GetMapping(value = "/")
//    public String test(Model model){
//        model.addAttribute("name","hello huy");
//        return "admin/list-user.html";
//    }

//    @GetMapping("/user/list")
//    public String userList(Model model){
//        ResponseEntity<ResponseObject> userEntityList = userService.getListUser();
//        model.addAttribute("userList", userEntityList.getBody().getData());
//        model.addAttribute("message", userEntityList.getBody().getMessage());
//        return "admin/list-user.html";
//    }

//    @GetMapping(value = "/management")
//    public String manager(Model model){
//        model.addAttribute("name","hello huy");
//        return "tesr2.html";
//    }

//    @PostMapping("/create-user")
//    public ResponseEntity<ResponseObject> createUser(@RequestBody UserEntity userEntity){
//        UserEntity user = userRepository.save(userEntity);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "create user successfully!", user)
//        );
//    }
}
