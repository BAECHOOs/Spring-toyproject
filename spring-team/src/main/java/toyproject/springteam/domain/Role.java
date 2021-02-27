package toyproject.springteam.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(schema = "baechoo", name = "Role")
@DynamicInsert
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /*@OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;*/

    public void setId(long l) {
        roleId = l;
    }
}
