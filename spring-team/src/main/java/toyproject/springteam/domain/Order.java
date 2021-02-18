package toyproject.springteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Orders")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "purchase_done", nullable = false)
    private Long purchaseDone;

    @Builder
    public Order(Long userId, Long productId, Long purchaseDone) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseDone = purchaseDone;
    }
}
