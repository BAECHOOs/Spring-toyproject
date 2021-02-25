package toyproject.springteam.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toyproject.springteam.domain.Product;

@Getter
@Setter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private String title;
    private Long price;
    private String description;
    private String pictureUrl;

    @Builder
    public ProductSaveRequestDto(String title, Long price,
                                 String description, String pictureUrl){
        this.title = title;
        this.price = price;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }

    public Product toEntity(){
        return Product.ProductBuilder()
                .title(title)
                .price(price)
                .description(description)
                .pictureUrl(pictureUrl)
                .build();
    }
}
