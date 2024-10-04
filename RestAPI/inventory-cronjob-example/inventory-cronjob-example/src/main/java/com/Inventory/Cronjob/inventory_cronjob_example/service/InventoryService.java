package com.Inventory.Cronjob.inventory_cronjob_example.service;

import com.Inventory.Cronjob.inventory_cronjob_example.model.Productsss;
import com.Inventory.Cronjob.inventory_cronjob_example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class InventoryService {



        private ProductRepository productRepository;
        public InventoryService(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }
       private JavaMailSender javaMailSender;
        public InventoryService(JavaMailSender javaMailSender){
            this.javaMailSender=javaMailSender;
        }

        @Value("${spring.mail.username}")
        private String fromEmail;

        private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        public void sendDeleteNotification() {
            LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
            List<Productsss> products = productRepository.findByCreatedDateBefore(endOfMonth.minusMonths(6)); // Example: delete after 6 months
            for (Productsss product : products) {
                // Here you can delete the product or just send a notification
                sendEmail("heenadongarkar@benchmarkit.solutions", "Delete Product Notification",
                        "Product " + product.getName() + " is due for deletion.");
            }
        }

        public void sendAddNotification() {
            // Send notification for adding new products
            sendEmail("heenadongarkar@benchmarkit.solutions", "Add Product Notification",
                    "It's time to add new products to inventory for the month.");
        }

        @Async
        public void addProduct(Productsss product) {
            // Save the new product in the database
            productRepository.save(product);

            // Schedule the email notification to be sent after 5 minutes
            executorService.schedule(() -> {
                sendEmail("heenadongarkar@benchmarkit.solutions", "Product Added Notification",
                        "Product " + product.getName() + " has been added to the inventory.");
            }, 5, TimeUnit.MINUTES);
        }

        private void sendEmail(String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        }
    }


