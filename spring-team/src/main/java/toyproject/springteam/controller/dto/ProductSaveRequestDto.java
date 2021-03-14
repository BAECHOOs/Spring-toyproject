package toyproject.springteam.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toyproject.springteam.domain.Product;
import toyproject.springteam.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private String title;
    private Long price;
    private String description;
    private String pictureUrl;
    private User user;

    @Builder
    public ProductSaveRequestDto(String title, Long price,
                                 String description, String pictureUrl, User user){
        this.title = title;
        this.price = price;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.user = user;
    }

    public Product toEntity(){
        return Product.ProductBuilder()
                .title(title)
                .price(price)
                .description(description)
                .pictureUrl(pictureUrl)
                .user(user)
                .build();
    }
}
