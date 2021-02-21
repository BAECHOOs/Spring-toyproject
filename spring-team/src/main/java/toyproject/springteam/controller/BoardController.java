package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.springteam.domain.Product;
import toyproject.springteam.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ProductService productService;

    @GetMapping("detail/{num}")
    public String detail(@PathVariable("num") Long id, Model model){
        Optional<Product> prod = productService.findById(id);
        model.addAttribute("prod", prod);
        return "board/detail";
    }

    @GetMapping("list")
    public String list(){
        return "board/list";
    }

    @GetMapping("gallery")
    public String gallery(){
        return "board/gallery";
    }
}
