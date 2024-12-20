package com.microstore.repository;

import com.microstore.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findByCategory(String category);
    List<Products> findByNameIgnoreCase(String name);
    List<Products> findByPrice(BigDecimal price);
}
