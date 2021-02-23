package toyproject.springteam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "baechoo", name = "Product")
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    //@Column(name = "user_id", nullable = false)
    //private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    @ColumnDefault("0")
    private Long price;

    @Column(name = "description")
    @ColumnDefault("NULL")
    private String description;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    /*@Column(name = "upload_date", nullable = false)
    private Date uploadDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;*/

    @Column(name = "view_count", nullable = false)
    @ColumnDefault("0")
    private Long viewCount;

    @Column(name = "purchase_done")
    @ColumnDefault("false")
    private Boolean purchaseDone;

    @Column(name = "like_count", nullable = false)
    @ColumnDefault("0")
    private Long likeCount;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id") // Product는 User의 user_id를 참조하는 외래키를 가짐 // 컬럼이름
    private User user;

    @Builder
    public Product(String title, Long price, String description,
                   String pictureUrl, //Date uploadDate, Date updateDate,
                   Long viewCount, Boolean purchaseDone, Long likeCount, Long orderId) {
        //this.userId = userId;
        this.title = title;
        this.price = price;
        this.description = description;
        this.pictureUrl = pictureUrl;
        //this.uploadDate = uploadDate;
        //this.updateDate = updateDate;
        this.viewCount = viewCount;
        this.purchaseDone = purchaseDone;
        this.likeCount = likeCount;
        this.orderId = orderId;
    }


}
