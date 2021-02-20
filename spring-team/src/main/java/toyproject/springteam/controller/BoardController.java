package toyproject.springteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("detail")
    public String detail(){
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
