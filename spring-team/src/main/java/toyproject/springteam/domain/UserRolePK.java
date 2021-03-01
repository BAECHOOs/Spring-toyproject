package toyproject.springteam.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class UserRolePK implements Serializable {
    private Long user;
    private  Long role;
}
