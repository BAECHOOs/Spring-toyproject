package toyproject.springteam.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
//@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private Long likeId;

    /*@Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "product_id",nullable = false)
    private Long productId;*/

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Like(){ //Long userId, Long productId
        //this.userId = userId;
        //this.productId = productId;
    }
}
