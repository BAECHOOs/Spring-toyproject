package toyproject.springteam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.springteam.controller.dto.ProductListResponseDto;
import toyproject.springteam.repository.LikeRepository;
import toyproject.springteam.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public List<ProductListResponseDto> getOrderedProducts(Long id){
        return orderRepository.findOrderedProducts(id).stream()
                .map(ProductListResponseDto::new)
                .collect(Collectors.toList());
    }

}
