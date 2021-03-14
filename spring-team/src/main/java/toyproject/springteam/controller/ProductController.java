package toyproject.springteam.controller;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import toyproject.springteam.controller.dto.ProductSaveRequestDto;
import toyproject.springteam.domain.User;
import toyproject.springteam.service.ProductService;
import toyproject.springteam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private UserService userService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final S3PresignedURL s3PresignedURL;

    @GetMapping("/new")
    public String createProductForm(HttpServletRequest request, Model model) {
        String userId = request.getRemoteUser();
        if (userId == null) {
            return "/account/login";
        }
        else {
            Long id = Long.parseLong(userId);
            model.addAttribute("user", userService.findById(id));
        }
        return "products/new";
    }

    @PostMapping("/new")
    public String createProduct(HttpServletRequest request, Model model, ProductSaveRequestDto requestDto, MultipartFile multipartFile) throws IOException, IllegalStateException {
        String userId = request.getRemoteUser();
        if (userId != null) {
            Long id = Long.parseLong(userId);
            model.addAttribute("user", userService.findById(id));
        }

        User user = userService.findUserById(Long.parseLong(userId));
        // S3 디렉토리 구성: 사용자 아이디/물건 아이디/이미지 아이디
        // 이미지는 판매글 당 1개만 업로드 가능 -> 아이디: 1
        String objectKey = userId+"/"+productService.findProctId()+"/1.jpg";
        String s3Url = s3PresignedURL.downloadS3(objectKey, multipartFile);
        requestDto.setPictureUrl(s3Url);
        requestDto.setUser(user);
        productService.saveProduct(requestDto);
        return "redirect:/"; //home 화면으로 이동
    }

}

