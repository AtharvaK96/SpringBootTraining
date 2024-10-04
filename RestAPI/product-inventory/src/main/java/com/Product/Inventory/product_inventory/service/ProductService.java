package com.Product.Inventory.product_inventory.service;

import com.Product.Inventory.product_inventory.exception.CategoryNotFoundException;
import com.Product.Inventory.product_inventory.exception.PriceRangeException;
import com.Product.Inventory.product_inventory.exception.StockNotFoundException;
import com.Product.Inventory.product_inventory.model.Product;
import com.Product.Inventory.product_inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service

public class ProductService {

    @Value("${file.upload-dir}")
    String uploadDir;
    private ProductRepository productRepository;
    private JavaMailSender javaMailSender;

    public ProductService(ProductRepository productRepository, JavaMailSender javaMailSender) {
        this.productRepository = productRepository;
        this.javaMailSender = javaMailSender;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    public List<Product> filterProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new CategoryNotFoundException("No products found for category: " + category);
        }
        return products;
    }

    public List<Product> filterProductsByPriceRange(Integer minPrice, Integer maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        if (products.isEmpty()) {
            throw new PriceRangeException("No products found in the price range: " + minPrice + " - " + maxPrice);
        }
        return products;
    }

    public List<Product> filterProductsByStock(int stock) {
        List<Product> products = productRepository.findByStockGreaterThan(stock);
        if (products.isEmpty()) {
            throw new StockNotFoundException("No products available with stock greater than: " + stock);
        }
        return products;
    }

    public Product addProduct(String name, String category, int price, int stock, MultipartFile photo) throws Exception {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setStock(stock);

        String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        photo.transferTo(filePath);
        product.setImageUrl(filePath.toString());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void sendEmailsForRecentlyAddedProducts() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fiveMinutesAgo = now.minusMinutes(5);

        List<Product> products = productRepository.findByCreatedAtBetween(fiveMinutesAgo.minusSeconds(30), fiveMinutesAgo.plusSeconds(30));

        for (Product product : products) {
            sendSimpleEmail("dongarkarheena6@gmail.com", "Product Added",
                    "A new product has been added: " + product.getName());
            System.out.println("Email sent for product: " + product.getName());
        }
    }

    private void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("heenadongarkar@benchmarkit.solutions");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void sendMonthlyAddProductEmail() {
        sendSimpleEmail("dongarkarheena6@gmail.com",
                "New Product Reminder",
                "It's time to add new products this month.");
    }

    @Scheduled(cron = "0 0 0 L * ?")
    public void sendMonthlyDeleteProductEmail() {
        sendSimpleEmail("dongarkarheena6@gmail.com",
                "Product Deletion Reminder",
                "It's time to review and delete old products this month.");
    }
}





