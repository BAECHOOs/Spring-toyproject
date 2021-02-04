package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import toyproject.springteam.domain.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long>{
    @Query("SELECT p FROM Posts p ORDER BY p.id")
    List<Like> findAll();
}
