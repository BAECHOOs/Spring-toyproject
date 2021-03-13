package toyproject.springteam.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.springteam.controller.dto.ProductListResponseDto;
import toyproject.springteam.controller.dto.ProductSaveRequestDto;
import toyproject.springteam.domain.Product;
import toyproject.springteam.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Long saveProduct(ProductSaveRequestDto requestDto) {
        return productRepository.save(requestDto.toEntity()).getProductId();
    }

    @Transactional(readOnly = true)
    public List<ProductListResponseDto> findRecentProducts(){
        return productRepository.findRecentProducts().stream()
                .map(ProductListResponseDto::new)
                .collect(Collectors.toList());
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
