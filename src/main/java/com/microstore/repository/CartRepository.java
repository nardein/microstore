package com.microstore.repository;

import com.microstore.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Carts,Long> {

    List<Carts> findByUserId(Long userId);

}
