package toyproject.springteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("board_read")
    public String read(){
        return "board/board_read";
    }

    @GetMapping("board_list")
    public String list(){
        return "board/board_list";
    }

    @GetMapping("board_gallery")
    public String gallery(){
        return "board/board_gallery";
    }
}
