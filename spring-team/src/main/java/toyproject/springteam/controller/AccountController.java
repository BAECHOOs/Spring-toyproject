package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.springteam.domain.User;
import toyproject.springteam.service.LikeService;
import toyproject.springteam.service.OrderService;
import toyproject.springteam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/join")
    public String join() {
        return "account/join";
    }

    @GetMapping("/loginTest")
    public String loginTest(HttpServletRequest request, Model model) {
        String user_id = request.getRemoteUser();
        if (user_id != null) {
            Long id = Long.parseLong(user_id);
            model.addAttribute("user", userService.findById(id));
        }
        return "account/loginTest";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/account/login";
    }

    @PostMapping("/join")
    public String join(User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getRemoteUser());
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("likedProducts", likeService.getLikedProducts(id));
        model.addAttribute("orderedProducts", orderService.getOrderedProducts(id));
        return "account/mypage";
    }
}
