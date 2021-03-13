package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toyproject.springteam.domain.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p ORDER BY p.uploadDate DESC")
    List<Product> findRecentProducts();

    Optional<Product> findById(Long id);

    @Query("SELECT MAX(productId) FROM Product")
    Long findMaxProductId();
}
