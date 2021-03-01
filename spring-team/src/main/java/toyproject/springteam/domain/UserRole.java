package toyproject.springteam.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "User_Role")
@IdClass(UserRolePK.class)
@DynamicInsert
public class UserRole {
    @Id
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;
}
