package t3h.vn.makecomfortstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t3h.vn.makecomfortstore.dto.UserDto;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/management")
    public String manager(){
        return "common/fragments.html";
    }
}
