package com.microstore.services;

import com.microstore.DTO.OrderItemsDTO;
import com.microstore.repository.OrderItemsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemsRepository orderItemsRepository;
    private final ModelMapper modelMapper;

    public OrderItemService(OrderItemsRepository orderItemsRepository, ModelMapper modelMapper) {
        this.orderItemsRepository = orderItemsRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderItemsDTO> getAllOrderItems(){
        return orderItemsRepository.findAll()
                .stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemsDTO.class))
                .collect(Collectors.toList());
    }
}
