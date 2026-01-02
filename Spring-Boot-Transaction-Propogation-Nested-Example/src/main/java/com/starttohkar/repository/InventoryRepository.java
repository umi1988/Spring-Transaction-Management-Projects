package com.starttohkar.repository;

import com.starttohkar.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Product, Integer> {
}
