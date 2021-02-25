package toyproject.springteam.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyproject.springteam.domain.Product;
import toyproject.springteam.repository.ProductRepository;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveProduct() {
        productRepository.save(Product.ProductBuilder()
                .title("banana")
                .price(5000L)
                .pictureUrl("pictures")
                .description("쌉니다")

        .build());
    }
}
