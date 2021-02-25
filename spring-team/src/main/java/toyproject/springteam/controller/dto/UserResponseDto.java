package toyproject.springteam.controller.dto;

import lombok.Getter;
import toyproject.springteam.domain.User;

@Getter
public class UserResponseDto {
    private Long userId;
    private String nickname;
    private String email;

    public UserResponseDto(User entity){
        this.userId = entity.getUserId();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
    }
}
