package toyproject.springteam.controller.dto;

import lombok.Getter;
import toyproject.springteam.domain.Product;

import java.util.Date;

@Getter
public class ProductListResponseDto {
    private Long product_id;
    private Long user_id;
    private String title;
    private Long price;
    private String description;
    private String picture_url;
    private Date upload_date;
    private Date update_date;
    private Long view_count;
    private Boolean purchase_done;
    private Long like_count;
    private Long order_id;

    public ProductListResponseDto(Product entity){
        this.product_id=entity.getProduct_id();
        this.user_id = entity.getUser_id();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.description = entity.getDescription();
        this.picture_url = entity.getPicture_url();
        this.update_date = entity.getUpdate_date();
        this.upload_date = entity.getUpload_date();
        this.view_count = entity.getView_count();
        this.purchase_done = entity.getPurchase_done();
        this.like_count = entity.getLike_count();
        this.order_id = entity.getOrder_id();
    }

}
