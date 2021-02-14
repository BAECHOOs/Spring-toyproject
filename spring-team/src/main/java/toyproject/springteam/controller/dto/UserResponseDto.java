package toyproject.springteam.controller.dto;

import lombok.Getter;
import toyproject.springteam.domain.User;

@Getter
public class UserResponseDto {
    private Long user_id;
    private String nickname;
    private String email;

    public UserResponseDto(User entity){
        this.user_id = entity.getUser_id();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
    }
}
