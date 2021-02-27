package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.springteam.service.ProductService;
import toyproject.springteam.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/", "/index", "/home"})
    public String getHome(HttpServletRequest request, Model model) {
        String user_id = request.getRemoteUser();
        if (user_id != null) {
            Long id = Long.parseLong(user_id);
            model.addAttribute("user", userService.findById(id));
        }
        model.addAttribute("products", productService.findRecentProducts());
        return "home";
    }

}
