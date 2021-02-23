package toyproject.springteam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toyproject.springteam.domain.Like;
import toyproject.springteam.domain.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveRequestDto {
    private String title;
    private Long price;
    private String description;
    private String pictureUrl;

    public Product toEntity(){
        return Product.builder()
                .title(title)
                .price(price)
                .description(description)
                .pictureUrl(pictureUrl)
                .build();
    }
}
