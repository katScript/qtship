package com.spring.app.liquidity.service;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.liquidity.models.Liquidity;
import com.spring.app.liquidity.models.repository.LiquidityRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiquidityService {
    @Autowired
    LiquidityRepository liquidityRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderStatusService orderStatusService;

    public void createNewLiquiditySession() {
        List<Customer> customers = customerRepository.findAll();
        List<String> orderStatuses = new ArrayList<>();

        for (OrderStatus os : orderStatusService.getCompleteStatus())
            orderStatuses.add(os.getCode());

        for (Customer c : customers) {
            createNewLiquiditySessionForCustomer(c, orderStatuses);
        }
    }

    public void createNewLiquiditySessionForCustomer(Customer customer, List<String> status) {
        List<Order> orders = orderRepository.findByCustomerAndStatusNotIn(customer, status);
        if (orders.isEmpty())
            return;

        Double moneyReceivable = 0.0,
                ppPm = 0.0,
                ccCash = 0.0,
                ppCash = 0.0,
                forControl = 0.0,
                receivable = 0.0,
                couponCash = 0.0;

        for (Order o : orders) {
            moneyReceivable += o.getSubtotal();
            couponCash += o.getCouponFee();

            if (o.getShipPayer()) {
                ppCash += o.getShippingFee();
            } else {
                ccCash += o.getShippingFee();
            }
        }

        Liquidity liquidity = new Liquidity(
                customer,
                moneyReceivable,
                ppPm,
                ccCash,
                ppCash,
                forControl,
                receivable,
                couponCash
        );

        liquidityRepository.save(liquidity);
    }

    public void processLiquidity(Order order) {
        if (order.getStatus().equals("DONE")) {
            orderSuccess(order);
        } else if (order.getStatus().equals("CANCEL")) {
            orderCancel(order);
        }
    }

    public void orderSuccess(Order order) {
        Customer customer = order.getCustomer();

        Liquidity lq = liquidityRepository.findTopByCustomerOrderByCreatedAtDesc(customer)
                .orElse(null);

        if (lq != null) {
            Double forControl = lq.getForControl(),
                receivable = lq.getReceivable();

            forControl += order.getSubtotal() - order.getShippingFee();
            receivable += forControl - (lq.getPpPm() + lq.getCcCash() + lq.getPpCash());

            lq.setForControl(forControl).setReceivable(receivable);
            liquidityRepository.save(lq);
        }
    }

    public void orderCancel(Order order) {
        Customer customer = order.getCustomer();

        Liquidity lq = liquidityRepository.findTopByCustomerOrderByCreatedAtDesc(customer)
                .orElse(null);

        if (lq != null) {
            Double moneyReceivable = lq.getMoneyReceivable(),
                    couponCash = lq.getCouponCash(),
                    receivable = lq.getReceivable();

            moneyReceivable -= order.getSubtotal();
            couponCash -= order.getCouponFee();
            receivable += lq.getForControl() - (lq.getPpPm() + lq.getCcCash() + lq.getPpCash());

            lq.setMoneyReceivable(moneyReceivable)
                    .setCouponCash(couponCash)
                    .setReceivable(receivable);

            liquidityRepository.save(lq);
        }
    }
}
