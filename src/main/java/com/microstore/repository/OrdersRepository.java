package com.microstore.repository;

import com.microstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long userId);
    List<Orders> findByStatus(String status);
    List<Orders> findByTotal(int total);
}
