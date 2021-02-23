package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import toyproject.springteam.controller.dto.ProductSaveRequestDto;
import toyproject.springteam.domain.Product;
import toyproject.springteam.service.ProductService;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/baechoo/products/new")
    public String createProductForm() {
        return "products/createProductForm";
    }


    /*private String title;
    private Long price;
    private String description;
    private String pictureUrl;


    @PostMapping("/baechoo/products/new")
    public String saveProduct(@RequestBody ProductSaveRequestDto form) {


        return "redirect:/baechoo/home"; //home 화면으로 이동
    }*/
}
