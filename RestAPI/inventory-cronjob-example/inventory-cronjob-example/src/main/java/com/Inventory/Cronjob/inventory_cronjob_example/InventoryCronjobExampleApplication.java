package com.Inventory.Cronjob.inventory_cronjob_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class InventoryCronjobExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryCronjobExampleApplication.class, args);
	}

}
