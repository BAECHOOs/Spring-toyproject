package toyproject.springteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import toyproject.springteam.controller.dto.ProductSaveRequestDto;
import toyproject.springteam.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/new")
    public String createProductForm() {
        return "products/new.html";
    }

    @PostMapping("/new")
    public String createProduct(ProductSaveRequestDto requestDto) {
        productService.saveProduct(requestDto);
        return "redirect:/"; //home 화면으로 이동
    }
}

