package toyproject.springteam.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(schema = "baechoo", name = "User")
@DynamicInsert
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user") // Product에서 User를 참조한 이름: user (private User user)
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    @Builder
    public User(String nickname, String email, String password, Boolean enabled, Role role){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles.add(role);
    }
}
