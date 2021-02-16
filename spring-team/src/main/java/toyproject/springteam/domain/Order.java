package toyproject.springteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Order")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "product_id", nullable = false)
    private Long product_id;

    @Column(name = "purchased_done", nullable = false)
    private Long purchased_done;

    @Builder
    public Order(Long user_id, Long product_id, Long purchased_done) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.purchased_done = purchased_done;
    }
}
