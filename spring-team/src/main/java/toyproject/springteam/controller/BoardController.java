package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.springteam.domain.Product;
import toyproject.springteam.service.ProductService;
import toyproject.springteam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("detail/{num}")
    public String detail(@PathVariable("num") Long id, Model model){
        Optional<Product> prod = productService.findById(id);
        model.addAttribute("prod", prod);
        return "board/detail";
    }

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String user_id = request.getRemoteUser();
        if (user_id != null) {
            Long id = Long.parseLong(user_id);
            model.addAttribute("nickname", userService.findById(id).getNickname());
        }
        model.addAttribute("products", productService.findRecentProducts());
        return "board/list";
    }

    @GetMapping("/gallery")
    public String gallery(){
        return "board/gallery";
    }
}
