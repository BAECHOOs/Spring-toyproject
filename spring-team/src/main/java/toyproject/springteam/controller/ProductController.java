package toyproject.springteam.controller;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import toyproject.springteam.controller.dto.ProductSaveRequestDto;
import toyproject.springteam.service.ProductService;
import toyproject.springteam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private UserService userService;
    @Autowired
    private final ProductService productService;
    private final S3PresignedURL s3PresignedURL;
    //@Autowired
    /*public ProductController(ProductService productService) {
        this.productService = productService;
    }*/


    @GetMapping("/new")
    public String createProductForm(HttpServletRequest request, Model model) {
        String user_id = request.getRemoteUser();
        if (user_id != null) {
            Long id = Long.parseLong(user_id);
            model.addAttribute("user", userService.findById(id));
        }
        return "products/new";
    }


    @PostMapping("/new")
    public String createProduct(HttpServletRequest request, Model model, ProductSaveRequestDto requestDto, MultipartFile multipartFile) throws IOException, IllegalStateException {
        String user_id = request.getRemoteUser();
        if (user_id != null) {
            Long id = Long.parseLong(user_id);
            model.addAttribute("user", userService.findById(id));
        }
        s3PresignedURL.downloadS3("", multipartFile);
        requestDto.setPictureUrl("https://");
        productService.saveProduct(requestDto);
        return "redirect:/"; //home 화면으로 이동
    }

}

