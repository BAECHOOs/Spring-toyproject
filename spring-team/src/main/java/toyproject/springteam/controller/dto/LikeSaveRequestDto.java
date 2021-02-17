package toyproject.springteam.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject.springteam.domain.Like;

@Getter
@NoArgsConstructor
public class LikeSaveRequestDto {
    private Long userId;
    private Long productId;

    @Builder
    public LikeSaveRequestDto(Long userId, Long productId){
        this.userId = userId;
        this.productId = productId;
    }

    public Like toEntity(){
        return Like.builder()
                .userId(userId)
                .productId(productId)
                .build();
    }

}
