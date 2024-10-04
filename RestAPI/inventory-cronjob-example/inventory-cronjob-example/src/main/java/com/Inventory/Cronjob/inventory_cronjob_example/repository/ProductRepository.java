package com.Inventory.Cronjob.inventory_cronjob_example.repository;

import com.Inventory.Cronjob.inventory_cronjob_example.model.Productsss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Productsss, Long> {
    List<Productsss> findByCreatedDateBefore(LocalDate date);
}
