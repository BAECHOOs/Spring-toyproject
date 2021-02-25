package toyproject.springteam.controller.dto;

import lombok.Getter;
import toyproject.springteam.domain.Product;
import toyproject.springteam.util.PriceFormat;
import toyproject.springteam.util.TimeFormat;

import java.time.LocalDateTime;

@Getter
public class ProductListResponseDto {
    private Long productId;
    //private Long userId;
    private String title;
    private String price;
    private String description;
    private String pictureUrl;
    private LocalDateTime updateDate;
    private String uploadDate;
    private Long viewCount;
    private Boolean purchaseDone;
    private Long likeCount;
    private Long orderId;

    public ProductListResponseDto(Product entity){
        this.productId =entity.getProductId();
        //this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.price = PriceFormat.format(entity.getPrice());
        this.description = entity.getDescription();
        this.pictureUrl = entity.getPictureUrl();
        this.updateDate = entity.getUpdateDate();
        this.uploadDate = TimeFormat.formatTimeString(entity.getUploadDate());
        this.viewCount = entity.getViewCount();
        this.purchaseDone = entity.getPurchaseDone();
        this.likeCount = entity.getLikeCount();
        this.orderId = entity.getOrderId();
    }

}
