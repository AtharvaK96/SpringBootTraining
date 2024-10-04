package com.Product.Inventory.product_inventory.controller;

import com.Product.Inventory.product_inventory.model.Product;
import com.Product.Inventory.product_inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public List<Product> getTheAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.filterProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam Integer min, @RequestParam Integer max) {
        return productService.filterProductsByPriceRange(min, max);
    }

    @GetMapping("/stock/{stock}")
    public List<Product> getProductsByStock(@PathVariable int stock) {
        return productService.filterProductsByStock(stock);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProductImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") int price,
            @RequestParam("stock") int stock) {
        {
            try {
                productService.addProduct(name, category, price, stock, file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok("Product added");

        }

    }
}