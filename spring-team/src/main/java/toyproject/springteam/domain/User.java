package toyproject.springteam.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Product")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String nickname;
    private String email;
    private String password;
    private Boolean enabled;

    @Builder
    public User(String nickname, String email, String password, Boolean enabled, Role role){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles.add(role);
    }

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
