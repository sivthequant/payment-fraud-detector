package com.sivthequant.productservice.service;

import com.sivthequant.productservice.exception.DuplicateResourceException;
import com.sivthequant.productservice.exception.ResourceNotFoundException;
import com.sivthequant.productservice.model.Product;
import com.sivthequant.productservice.model.ProductRequest;
import com.sivthequant.productservice.model.ProductResponse;
import com.sivthequant.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductResponse createProduct(ProductRequest request) {
        String name = request.name();
        if(productRepository.existsByName(name)){
            throw new DuplicateResourceException("Product with name '" + name + "' already exists.");
        }
        Product product = new Product();
        apply(request, product);
        return ProductResponse.from(productRepository.save(product));
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        if(productRepository.existsById(id)){
            Product product = findProduct(id);
            apply(request, product);
            return ProductResponse.from(productRepository.saveAndFlush(product));
        }
        throw new ResourceNotFoundException("Product with " +id + " not found");
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }

    @Override
    public ProductResponse findProductById(Long id) {
        return ProductResponse.from(findProduct(id));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = findProduct(id);
        productRepository.delete(product);
    }

    private Product findProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with " +id + " not found"));
    }

    private static void apply(ProductRequest request, Product product){
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
    }
}
