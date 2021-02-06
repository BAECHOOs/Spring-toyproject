package toyproject.springteam.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.springteam.domain.Like;
import toyproject.springteam.repository.LikeRepository;
import toyproject.springteam.controller.dto.LikeSaveRequestDto;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public Long save(LikeSaveRequestDto requestDto){
        return likeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id){
        Like like = likeRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        likeRepository.delete(like);
    }


}
