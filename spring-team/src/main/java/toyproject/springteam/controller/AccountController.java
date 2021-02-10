package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.springteam.domain.User;
import toyproject.springteam.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/join")
    public String join() {
        return "account/join";
    }



    @PostMapping("/join")
    public String join(User user) {
        userService.save(user);
        return "redirect:/";
    }
}
