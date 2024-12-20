package com.microstore.services;

import com.microstore.DTO.CartsDTO;
import com.microstore.entity.Carts;
import com.microstore.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    public CartService(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    public List<CartsDTO> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cart -> modelMapper.map(cart, CartsDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean addCart(CartsDTO cartDTO) {
        Carts cart = modelMapper.map(cartDTO, Carts.class);
        cart.setDate(new Date());
        cart = cartRepository.save(cart);
        return true;
    }

    @Transactional
    public boolean updateCart(CartsDTO cartDTO) {
        Carts cart = modelMapper.map(cartDTO, Carts.class);
        cart.setDate(new Date());
        cart = cartRepository.save(cart);
        return true;
    }

    @Transactional
    public boolean deleteCart(CartsDTO cartDTO) {
        Carts cart = modelMapper.map(cartDTO, Carts.class);
        cartRepository.delete(cart);
        return true;
    }
}
