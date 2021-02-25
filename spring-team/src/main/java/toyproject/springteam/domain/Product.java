package toyproject.springteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import toyproject.springteam.domain.BaseTimeEntity;
import toyproject.springteam.domain.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Product")
@DynamicInsert //DB에 설정한 Default 값을 저장 // 기본적으로는 DB에 Null 저장
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "description")
    @ColumnDefault("NULL")
    private String description;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @Column(name = "view_count")
    @ColumnDefault("0")
    private Long viewCount;

    @Column(name = "purchase_done")
    @ColumnDefault("false")
    private Boolean purchaseDone;

    @Column(name = "like_count")
    @ColumnDefault("0")
    private Long likeCount;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder(builderClassName = "ProductBuilder", builderMethodName = "ProductBuilder")
    public Product(String title, Long price, String description, String pictureUrl,
                   Long viewCount, Boolean purchaseDone, Long likeCount, Long orderId, User user) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.viewCount = viewCount;
        this.purchaseDone = purchaseDone;
        this.likeCount = likeCount;
        this.orderId = orderId;
        this.user = user;
    }
}
