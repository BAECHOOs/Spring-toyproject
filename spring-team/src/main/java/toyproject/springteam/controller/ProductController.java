package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.springteam.controller.dto.ProductForm;
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

    @GetMapping("/baechoo/products/new")
    public String create(ProductForm form) {
        Product product = new Product();
        product.setTitle(form.getTitle());

        memberService.join(member);

        return "redirect:/baechoo/home"; //home 화면으로 이동
    }*/
}
