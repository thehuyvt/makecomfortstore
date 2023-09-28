package t3h.vn.makecomfortstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.makecomfortstore.dto.UserDto;
import t3h.vn.makecomfortstore.response.ResponseObject;
import t3h.vn.makecomfortstore.service.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String createUser(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register.html";
    }

    @PostMapping("/register/save")
    public String saveUser(@Valid UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            return "register.html";
        }
        ResponseEntity<ResponseObject> responseEntity = userService.saveUser(userDto);
        if (responseEntity.getBody().getStatus().equals("success")){
            redirectAttributes.addAttribute("message", responseEntity.getBody().getMessage());
            return "redirect:/login";
        }else{
            model.addAttribute("error", responseEntity.getBody().getMessage());
            return "register.html";
        }

    }
}
