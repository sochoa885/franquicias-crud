package com.franchises.franchises_crud.services;

import com.franchises.franchises_crud.modules.BranchModel;
import com.franchises.franchises_crud.modules.ProductModel;
import com.franchises.franchises_crud.repositories.IBranchRepository;
import com.franchises.franchises_crud.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private final IProductRepository productRepository;

    @Autowired
    private final IBranchRepository branchRepository;

    public ProductService(IProductRepository productRepository, IBranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    public Supplier<ProductModel> createProduct(Long branchId, ProductModel product) {
        BranchModel branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        product.setBranch(branch);
        return () -> productRepository.save(product);
    }

    public Supplier<ProductModel> deleteProduct(Long productId) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.deleteById((productId));
        return () -> product;
    }

    public Supplier<ProductModel> updateProductStock(Long productId, int stock) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(stock);
        return () -> productRepository.save(product);
    }

    public Supplier<ProductModel> updateProductName(Long productId, String name) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(name);
        return () -> productRepository.save(product);
    }
}
