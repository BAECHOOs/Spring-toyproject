package toyproject.springteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toyproject.springteam.controller.dto.LikeSaveRequestDto;
import toyproject.springteam.controller.dto.UserResponseDto;
import toyproject.springteam.domain.Product;
import toyproject.springteam.domain.User;
import toyproject.springteam.service.LikeService;
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

    @Autowired
    private LikeService likeService;

    @GetMapping("detail/{num}")
    public String detail(@PathVariable("num") Long prod_id, HttpServletRequest request, Model model){
        String user_id = request.getRemoteUser();
        UserResponseDto user = null;
        if (user_id != null) {
            Long id = Long.parseLong(user_id);
            user = userService.findById(id);
            model.addAttribute("user", userService.findById(id));
        }
        Optional<Product> prod = productService.findById(prod_id);
        model.addAttribute("prod", prod);

        boolean isOwner = false;
        if(user != null) {
            if (prod.get().getUser().getUserId() == Integer.parseInt(user_id)) {
                isOwner = true;
            }
        }

        model.addAttribute("isOwner", isOwner);
        return "board/detail";
    }

    @PostMapping("detail/{num}/likeOn")
    public void likeOn(@PathVariable("num") Long prod_id, HttpServletRequest request){
        Long user_id = Long.parseLong(request.getRemoteUser());
//        User user = userService.findById(user_id);

        // findByUserIdAndProductId 로 Like Entity를 찾아와야 delete한다.
//        likeService.findByUserIdAndProductId(user, product);


        LikeSaveRequestDto like = new LikeSaveRequestDto(user_id, prod_id);

        likeService.save(like);
    }

    @PostMapping("detail/{num}/likeOff")
    public void likeOff(@PathVariable("num") Long prod_id, HttpServletRequest request, @RequestBody int flag){
        Long user_id = Long.parseLong(request.getRemoteUser());
        LikeSaveRequestDto like = new LikeSaveRequestDto(user_id, prod_id);
        Long like_id = 0L;

        likeService.delete(like_id);
    }


    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String user_id = request.getRemoteUser();
        if (user_id != null) {
            Long id = Long.parseLong(user_id);
            model.addAttribute("user", userService.findById(id));
        }
        model.addAttribute("products", productService.findRecentProducts());
        return "board/list";
    }

    @GetMapping("/gallery")
    public String gallery(){
        return "board/gallery";
    }

}
