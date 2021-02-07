package toyproject.springteam.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject.springteam.domain.Like;

@Getter
@NoArgsConstructor
public class LikeSaveRequestDto {
    private Long user_id;
    private Long product_id;

    @Builder
    public LikeSaveRequestDto(Long user_id, Long product_id){
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public Like toEntity(){
        return Like.builder()
                .user_id(user_id)
                .product_id(product_id)
                .build();
    }

}
