package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import toyproject.springteam.domain.Order;
import toyproject.springteam.domain.Product;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT p FROM Order o JOIN o.product p WHERE o.user.userId=:id ORDER BY p.uploadDate DESC")
    List<Product> findOrderedProducts(@Param("id") Long id);
}
