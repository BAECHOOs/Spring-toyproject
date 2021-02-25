package toyproject.springteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Orders")
@DynamicInsert
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "purchase_done", nullable = false)
    @ColumnDefault("false")
    private Boolean purchaseDone;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Builder
    public Order(Boolean purchaseDone, User user, Product product) {
        this.purchaseDone = purchaseDone;
        this.user = user;
        this.product = product;
    }
}
