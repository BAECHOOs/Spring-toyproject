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
@Table(schema = "baechoo", name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "nickname", nullable = false, columnDefinition = "VARCHAR(40) NOT NULL")
    private String nickname;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(200) NOT NULL")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bit(1) NOT NULL DEFAULT 0")
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

    @OneToMany(mappedBy = "user") // Product에서 User를 참조한 이름: user (private User user)
    private List<Product> products;

}
