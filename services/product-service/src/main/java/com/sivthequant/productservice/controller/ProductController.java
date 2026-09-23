package com.sivthequant.productservice.controller;

import com.sivthequant.productservice.model.Product;
import com.sivthequant.productservice.model.ProductRequest;
import com.sivthequant.productservice.model.ProductResponse;
import com.sivthequant.productservice.service.ProductService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        ProductResponse createdProduct = productService.createProduct(productRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProduct.id()).toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @GetMapping()
    List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                  @Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
