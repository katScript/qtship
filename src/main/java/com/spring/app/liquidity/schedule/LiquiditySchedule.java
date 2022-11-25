package com.spring.app.liquidity.schedule;

import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.liquidity.models.Liquidity;
import com.spring.app.liquidity.models.repository.LiquidityRepository;
import com.spring.app.liquidity.service.LiquidityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LiquiditySchedule {
    private static final Logger logger = LoggerFactory.getLogger(LiquiditySchedule.class);

    @Autowired
    LiquidityService liquidityService;

    @Scheduled(cron = "0 0 * * *")
    public void createNewLiquiditySession() {
        logger.info("Create New Liquidity Session...");
        liquidityService.createNewLiquiditySession();
        logger.info("Create New Liquidity Session Success!");
    }
}
