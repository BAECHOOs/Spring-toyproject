package toyproject.springteam.repository;

import toyproject.springteam.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findAllByUserId(Long user_id);
    Optional<Order> findByProductId(Long product_id);
}
