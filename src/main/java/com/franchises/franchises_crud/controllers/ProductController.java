package com.franchises.franchises_crud.controllers;

import com.franchises.franchises_crud.modules.ProductModel;
import com.franchises.franchises_crud.services.ProductService;
import com.franchises.franchises_crud.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create/{branchId}")
    public ResponseEntity<ApiResponse<ProductModel>> createProduct(@PathVariable Long branchId, @RequestBody ProductModel product) {
        try {
            ApiResponse<ProductModel> response = new ApiResponse<>(true, "Product created successfully", productService.createProduct(branchId, product).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<ProductModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{productId}/delete")
    public ResponseEntity<ApiResponse<ProductModel>> deleteProduct(@PathVariable Long productId) {
        try {
            ApiResponse<ProductModel> response = new ApiResponse<>(true, "Product successfully removed", productService.deleteProduct(productId).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<ProductModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{productId}/update-stock")
    public ResponseEntity<ApiResponse<ProductModel>> updateProductStock(@PathVariable Long productId, @RequestParam int stock) {
        try {
            ApiResponse<ProductModel> response = new ApiResponse<>(true, "Product updated successfully", productService.updateProductStock(productId, stock).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<ProductModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{productId}/update-name")
    public ResponseEntity<ApiResponse<ProductModel>> updateProductName(@PathVariable Long productId, @RequestParam String name) {
        try {
            ApiResponse<ProductModel> response = new ApiResponse<>(true, "Product updated successfully", productService.updateProductName(productId, name).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<ProductModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
