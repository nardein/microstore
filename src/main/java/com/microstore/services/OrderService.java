package com.microstore.services;

import com.microstore.DTO.OrderDTO;
import com.microstore.entity.Orders;
import com.microstore.entity.Users;
import com.microstore.repository.OrdersRepository;
import com.microstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    //Dependency Injection
    public OrderService(OrdersRepository ordersRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.ordersRepository = ordersRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    public List<OrderDTO> getAllOrders(){
        return ordersRepository.findAll()
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public boolean createOrder(OrderDTO ordersDTO) {
        Optional<Users> usersOptional = userRepository.findById(ordersDTO.getUser_id());
        if (!usersOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        Orders orderEntity = modelMapper.map(ordersDTO, Orders.class);
        orderEntity.setUser(usersOptional.get());
        orderEntity.setCreated_at(new Date());
        ordersRepository.save(orderEntity);
        return true;
    }

    @Transactional
    public boolean updateOrder(Long id,OrderDTO ordersDTO) {
        Optional<Orders> orderEntity = ordersRepository.findById(id);
        if(orderEntity.isPresent()) {
            Orders orders = orderEntity.get();
            orders.setStatus(ordersDTO.getStatus());
            orders.setTotal(Integer.parseInt(String.valueOf(ordersDTO.getTotal())));
            orders.setCreated_at(new Date());
            ordersRepository.save(orders);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deleteOrder(Long id) {
        if(ordersRepository.existsById(id)) {
            ordersRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }
}
