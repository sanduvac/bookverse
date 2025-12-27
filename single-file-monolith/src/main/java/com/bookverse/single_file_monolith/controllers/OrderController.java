package com.bookverse.single_file_monolith.controllers;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookverse.single_file_monolith.messaging.OrderEventPublisher;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderEventPublisher publisher;

    public OrderController(OrderEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping
    public String createOrder(@RequestParam String product) {
        // Sahte bir sipariş oluşturalım
        Map<String, Object> order = new HashMap<>();
        order.put("id", 101);
        order.put("product", product);
        order.put("status", "CREATED");

        // Olayı fırlat!
        publisher.publishOrderCreatedEvent(order);
        
        return "Sipariş alındı ve olay gönderildi!";
    }
}