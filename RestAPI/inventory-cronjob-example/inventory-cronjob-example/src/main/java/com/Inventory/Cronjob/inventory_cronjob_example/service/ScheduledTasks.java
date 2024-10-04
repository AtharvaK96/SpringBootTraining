package com.Inventory.Cronjob.inventory_cronjob_example.service;

import com.Inventory.Cronjob.inventory_cronjob_example.model.Productsss;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class ScheduledTasks {

       private InventoryService inventoryService;
       public ScheduledTasks(InventoryService inventoryService){
           this.inventoryService=inventoryService;
       }

        // Run this at the last day of the month at 23:59
        @Scheduled(cron = "59 59 23 L * ?")
        public void scheduleDeleteNotification() {
            inventoryService.sendDeleteNotification();
        }

        // Run this at the first day of the month at 00:01
        @Scheduled(cron = "1 1 0 1 * ?")
        public void scheduleAddNotification() {
            Productsss newProduct = new Productsss();
            newProduct.setName("New Product");
            newProduct.setQuantity(100);
            newProduct.setCreatedDate(LocalDate.now());
            inventoryService.addProduct(newProduct);  // Add product and schedule notification
        }
    }


