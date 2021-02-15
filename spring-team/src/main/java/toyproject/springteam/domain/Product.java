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
    private Long product_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "description")
    private String description;

    @Column(name = "picture_url", nullable = false)
    private String picture_url;

    @Column(name = "upload_date", nullable = false)
    private Date upload_date;

    @Column(name = "update_date", nullable = false)
    private Date update_date;

    @Column(name = "view_count", nullable = false)
    private Long view_count;

    @Column(name = "purchase_done", nullable = false)
    private Boolean purchase_done;

    @Column(name = "like_count", nullable = false)
    private Long like_count;

    @Builder
    public Product(Long user_id, String title, Long price, String description,
                   String picture_url, Date upload_date, Date update_date,
                   Long view_count, Boolean purchase_done, Long like_count) {
        this.user_id = user_id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.picture_url = picture_url;
        this.upload_date = upload_date;
        this.update_date = update_date;
        this.view_count = view_count;
        this.purchase_done = purchase_done;
        this.like_count = like_count;
    }
}
