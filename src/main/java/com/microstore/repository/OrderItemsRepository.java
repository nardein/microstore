package com.microstore.repository;

import com.microstore.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{

    List<OrderItems> findByOrderId(Long orderId);
    List<OrderItems> findByProductId(Long productId);
    List<OrderItems> findByPrice(BigDecimal price);
}
