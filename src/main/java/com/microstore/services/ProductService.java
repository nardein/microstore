package com.microstore.services;

import com.microstore.DTO.ProductDTO;
import com.microstore.entity.Products;
import com.microstore.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;


    //Dependency Injection
    public ProductService(ProductsRepository productsRepository, ModelMapper modelMapper) {
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
    }

    // READ - Ottiene tutti i prodotti
    public List<ProductDTO> getAllProducts() {
        // Recupera tutti i prodotti dal database e li converte in DTO
        return productsRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productsRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .orElse(null);
    }

    // CREATE - Aggiunge un nuovo prodotto
    @Transactional
    public boolean createProduct(ProductDTO productDTO) {
        // Mappa il DTO all'entità e salva nel database
        Products productEntity = modelMapper.map(productDTO, Products.class);
        productEntity.setCreated_at(new Date());
        productsRepository.save(productEntity);
        return true;
    }

    @Transactional
    public boolean updateProduct(Long id, ProductDTO productDTO) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setCategory(productDTO.getCategory());
            product.setStock(productDTO.getStock());
            product.setCreated_at(new Date());
            productsRepository.save(product);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}