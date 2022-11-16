package com.spring.app.order.service;

import com.spring.app.customers.models.Customer;
import com.spring.app.order.models.Order;
import com.spring.app.orders.payload.OrderData;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public void saveGuestOrder(OrderData order) {
        Order _order = this.processOrder(order);
        this.orderRepository.save(_order);
    }

    public void saveCustomerOrder(OrderData order) {
        Customer customer = this.customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order _order = this.processOrder(order);
        _order.setCustomer(customer);

        this.orderRepository.save(_order);
    }
}
