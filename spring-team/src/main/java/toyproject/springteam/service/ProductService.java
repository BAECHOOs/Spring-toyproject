package toyproject.springteam.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.springteam.controller.dto.ProductListResponseDto;
import toyproject.springteam.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductListResponseDto> findRecentProducts(){
        return productRepository.findRecentProducts().stream()
                .map(ProductListResponseDto::new)
                .collect(Collectors.toList());
    }
}
