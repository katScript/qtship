package com.spring.app.orders.event;

import com.spring.app.liquidity.service.LiquidityService;
import com.spring.app.orders.models.Order;
import com.spring.app.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class OrderListener {
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Order order) {
        try {
            LiquidityService liquidityService = BeanUtil.getBean(LiquidityService.class);
            liquidityService.processLiquidity(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
