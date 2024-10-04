package com.Inventory.Cronjob.inventory_cronjob_example.controller;

import com.Inventory.Cronjob.inventory_cronjob_example.service.InventoryService;
import com.Inventory.Cronjob.inventory_cronjob_example.service.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {


        @Autowired
        private InventoryService inventoryService;
        @Autowired
        private ScheduledTasks scheduledTasks;

        // Endpoint to manually trigger sending the delete notification
        @PostMapping("/notify/delete")
        public ResponseEntity<String> notifyDeleteProducts() {
            inventoryService.sendDeleteNotification();
            return ResponseEntity.ok("Delete notification sent.");
        }

        // Endpoint to manually trigger sending the add notification
        @PostMapping("/notify/add")
        public ResponseEntity<String> notifyAddProducts() {
            inventoryService.sendAddNotification();
            return ResponseEntity.ok("Add notification sent.");
        }

        // Additional endpoints can be created based on other requirements, such as adding/deleting products
    }


