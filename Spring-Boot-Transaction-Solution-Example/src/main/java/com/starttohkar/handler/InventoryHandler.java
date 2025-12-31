package com.starttohkar.handler;

import com.starttohkar.entity.Product;
import com.starttohkar.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryHandler {

    private final InventoryRepository inventoryRepository;

    //constructor injection
    public InventoryHandler(InventoryRepository inventoryRepository){
        this.inventoryRepository=inventoryRepository;
    }

    public Product updateProductDetails(Product product){
        //Till now, it's working as expected as able to store the order and save the product with stock updated.
        // Now we need to replicate the use case to understand the need of txn mgmt
        // forcefully throwing exception to simulate use of tx
        if(product.getPrice()> 5000){
            throw new RuntimeException("DB Crashed ....");
        }
        return inventoryRepository.save(product);
    }

    public Product getProduct(int id){
        return inventoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not available with id : " + id));
    }
}
