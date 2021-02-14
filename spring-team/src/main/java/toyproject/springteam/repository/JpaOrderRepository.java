package toyproject.springteam.repository;

import toyproject.springteam.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class JpaOrderRepository implements OrderRepository {

    private final EntityManager em;

    public JpaOrderRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    @Override
    public Optional<Order> findAllByUserId(Long user_id) {
        List<Order> result = em.createQuery("select o from Order as o where o.user_id = :user_id", Order.class)
                .setParameter("user_id", user_id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Order> findByProductId(Long product_id) {
        Order order = em.find(Order.class, product_id);
        return Optional.ofNullable(order);
    }
}
