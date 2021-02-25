package toyproject.springteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.springteam.domain.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
