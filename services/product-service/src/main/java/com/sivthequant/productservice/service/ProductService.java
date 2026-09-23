package com.sivthequant.productservice.service;
import com.sivthequant.productservice.model.Product;

import com.sivthequant.productservice.model.ProductRequest;
import com.sivthequant.productservice.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService  {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    List<ProductResponse> getAllProducts();
    ProductResponse findProductById(Long id);
    void deleteProduct(Long id);
}
