package toyproject.springteam.controller;


import lombok.RequiredArgsConstructor;
import toyproject.springteam.service.LikeService;
import toyproject.springteam.controller.dto.LikeSaveRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeApiController {
    private final LikeService likeService;

    // url 임시로 작성한 것임
    @PostMapping("/api/like")
    public Long save(@RequestBody LikeSaveRequestDto requestDto){
        return likeService.save(requestDto);
    }

    @DeleteMapping("/api/like")
    public Long delete(@PathVariable Long likeId){
        likeService.delete(likeId);
        return likeId;
    }

    /* TODO : findById() 기능이 필요한지 잘 모르겠어서 구현 안함 */
}