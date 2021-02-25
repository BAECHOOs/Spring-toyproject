package toyproject.springteam.domain;


import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
//@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Likes")
@DynamicInsert
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private Long likeId;

    /*@Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "product_id",nullable = false)
    private Long productId;*/

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Builder
    public Like(User user, Product product){ //Long userId, Long productId
        this.user = user;
        this.product = product;
        //this.userId = userId;
        //this.productId = productId;
    }
}
