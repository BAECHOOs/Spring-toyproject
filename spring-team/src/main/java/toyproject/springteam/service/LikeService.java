package toyproject.springteam.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.springteam.domain.Like;
import toyproject.springteam.domain.Product;
import toyproject.springteam.domain.User;
import toyproject.springteam.repository.LikeRepository;
import toyproject.springteam.controller.dto.LikeSaveRequestDto;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public Long save(LikeSaveRequestDto requestDto) {
        return likeRepository.save(requestDto.toEntity()).getLikeId();
    }

    @Transactional
    public void delete(Long id){
        Like like = likeRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        likeRepository.delete(like);
    }

    public Optional<Like> findByUserIdAndProductId(User user, Product product){
        Optional<Like> like = likeRepository.findByUserIdAndProductId(user, product);
        return like;
    }
}
