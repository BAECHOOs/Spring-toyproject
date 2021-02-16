package toyproject.springteam.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private Long user_id;
    private Long product_id;
    private Long purchased_done;
}
