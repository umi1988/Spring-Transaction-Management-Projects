package com.starttohkar.handler;

import com.starttohkar.entity.AuditLog;
import com.starttohkar.entity.Order;
import com.starttohkar.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentValidatorHandler {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public void validatePayment(Order order){
        //Assume Payment processing happens here
        boolean paymentSuccessful = false;
        // if payment fail then we log the payment failure in the mandatory transaction
        if(!paymentSuccessful) {
            AuditLog auditLog = new AuditLog();
            auditLog.setOrderId(Long.valueOf(order.getId()));
            auditLog.setAction("Payment Failed for Order");
            auditLog.setTimestamp(LocalDateTime.now());

//            if(order.getTotalPrice()>1000){
//                throw new RuntimeException("Error in payment validator");
//            }
            //save the audit log
            auditLogRepository.save(auditLog);
        }
    }
}
