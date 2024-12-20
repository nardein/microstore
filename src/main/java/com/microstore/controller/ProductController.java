package com.microstore.controller;

import com.microstore.DTO.ProductDTO;
import com.microstore.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //READ Ottenere tutti i prodotti
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // READ - Ottenere un prodotto per ID
    @GetMapping("/id")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // CREATE - Aggiungere un nuovo prodotto
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Prodotto creato con successo.");
    }

    // UPDATE - Aggiornare un prodotto esistente
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestParam Long id, @RequestBody ProductDTO productDTO) {
        boolean isUpdated = productService.updateProduct(id, productDTO);
        if (isUpdated) {
            return ResponseEntity.ok("Prodotto aggiornato con successo.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodotto non trovato.");
        }
    }

    // DELETE - Eliminare un prodotto
    @DeleteMapping("/id")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.ok("Prodotto eliminato con successo.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodotto non trovato.");
        }
    }
}
