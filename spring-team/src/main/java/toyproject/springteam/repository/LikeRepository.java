package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toyproject.springteam.domain.Like;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long>{
    @Query("SELECT count(*) FROM Like WHERE product_id = product_id")
    Long findCountById(Long product_id);

    Optional<Like> findById(Long like_id);

    //SELECT count(*) FROM Like WHERE post_id = 'XXX'
}
