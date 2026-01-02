package com.starttohkar.service.isolation;

import com.starttohkar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepeatableReadDemo {

    @Autowired
    private ProductService productService;


    public void testRepeatedRead(int id) throws InterruptedException {
        // Start Transaction A (Thread 1) to update the stock but not commit, then roll back

        Thread threadA = new Thread(() -> {
            try{
                productService.updateStock(id, 5); // Change stock to 5
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        // Start Transaction B (Thread 2) to read the stock multiple times
        Thread threadB = new Thread(() -> {
            try {
                productService.fetchStock(id);  // Read stock during Transaction A
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        // Start the threads
        threadA.start();
        threadB.start();

        // Wait for threads to complete
        threadA.join();
        threadB.join();
    }




}