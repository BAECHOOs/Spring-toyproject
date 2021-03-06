package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toyproject.springteam.domain.Like;
import toyproject.springteam.domain.Product;
import toyproject.springteam.domain.User;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long>{
    @Query("SELECT count(*) FROM Likes WHERE product_id = product_id")
    Long findCountById(Long product_id);

    Optional<Like> findById(Long like_id);

    @Query("from Like " +
            "where user= :user and " +
            "      product= :product")
    Optional<Like> findByUserIdAndProductId(
            @Param("user") User user,
            @Param("product") Product product
    );
}
