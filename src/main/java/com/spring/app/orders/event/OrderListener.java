package com.spring.app.orders.event;

import com.spring.app.liquidity.service.LiquidityService;
import com.spring.app.orders.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class OrderListener {

    @Autowired
    LiquidityService liquidityService;

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Order order) {
        liquidityService.processLiquidity(order);
    }
}
