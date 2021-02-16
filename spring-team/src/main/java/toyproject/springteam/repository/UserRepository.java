package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.springteam.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
