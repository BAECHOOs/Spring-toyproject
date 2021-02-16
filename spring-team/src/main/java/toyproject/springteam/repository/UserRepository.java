package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.springteam.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
