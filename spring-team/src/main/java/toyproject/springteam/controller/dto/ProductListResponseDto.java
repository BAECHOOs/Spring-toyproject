package toyproject.springteam.controller.dto;

import lombok.Getter;
import toyproject.springteam.domain.Product;

import java.util.Date;

@Getter
public class ProductListResponseDto {
    private Long productId;
    //private Long userId;
    private String title;
    private Long price;
    private String description;
    private String pictureUrl;
    private Long viewCount;
    private Boolean purchaseDone;
    private Long likeCount;
    private Long orderId;

    public ProductListResponseDto(Product entity){
        this.productId =entity.getProductId();
        //this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.description = entity.getDescription();
        this.pictureUrl = entity.getPictureUrl();
        this.viewCount = entity.getViewCount();
        this.purchaseDone = entity.getPurchaseDone();
        this.likeCount = entity.getLikeCount();
        this.orderId = entity.getOrderId();
    }

}
