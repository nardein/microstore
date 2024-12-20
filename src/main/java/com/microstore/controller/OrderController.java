package com.microstore.controller;

import com.microstore.DTO.OrderDTO;
import com.microstore.services.OrderService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //READ - Ottieni tutti gli ordini
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    //CREATE - Crea gli ordini
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDTO orderdto){
        orderService.createOrder(orderdto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
    }

    //UPDATE - Aggiorna un ordine
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id,@RequestBody OrderDTO orderdto){
        boolean isUpdated = orderService.updateOrder(id, orderdto);
        if(isUpdated){
            return ResponseEntity.ok("Order updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    //DELETE - Elimina un ordine
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        boolean isDeleted = orderService.deleteOrder(id);
        if(isDeleted){
            return ResponseEntity.ok("Order deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }
}

