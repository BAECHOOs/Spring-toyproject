package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toyproject.springteam.domain.Like;
import toyproject.springteam.domain.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>{

    Optional<Like> findById(Long like_id);

    @Query("SELECT p FROM Like l JOIN l.product p WHERE l.user.userId=:id ORDER BY p.uploadDate DESC")
    List<Product> findLikedProducts(@Param("id") Long id);

    //SELECT count(*) FROM Like WHERE post_id = 'XXX'
}
