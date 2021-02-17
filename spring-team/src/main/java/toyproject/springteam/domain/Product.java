package toyproject.springteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "description")
    private String description;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @Column(name = "upload_date", nullable = false)
    private Date uploadDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @Column(name = "view_count", nullable = false)
    private Long viewCount;

    @Column(name = "purchase_done", nullable = true)
    private Boolean purchaseDone;

    @Column(name = "like_count", nullable = false)
    private Long likeCount;

    @Column(name = "order_id", nullable = true)
    private Long orderId;

    @Builder
    public Product(Long productId, Long userId, String title, Long price, String description,
                   String pictureUrl, Date uploadDate, Date updateDate,
                   Long viewCount, Boolean purchaseDone, Long likeCount, Long orderId) {
        this.productId = productId;
        this.userId = userId;
        this.title = title;
        this.price = price;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.uploadDate = uploadDate;
        this.updateDate = updateDate;
        this.viewCount = viewCount;
        this.purchaseDone = purchaseDone;
        this.likeCount = likeCount;
        this.orderId = orderId;
    }
}
