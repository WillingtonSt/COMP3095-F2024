package com.example.orderservice.service;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {

       var isProductinStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

       if(isProductinStock) {

           Order order = Order.builder()
                   .orderNumber(UUID.randomUUID().toString())
                   .price(orderRequest.price())
                   .skuCode(orderRequest.skuCode())
                   .quantity(orderRequest.quantity())
                   .build();

           orderRepository.save(order);

       } else {
           throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + "is not in stock");
       }
    }

}
