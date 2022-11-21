package com.spring.app.orders.services;

import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderLog;
import com.spring.app.orders.payload.OrderLogData;
import org.springframework.stereotype.Service;

@Service
public class OrderLogService {
    public OrderLogData processOrderLogResponse(OrderLog orderLog) {
        return new OrderLogData(
                orderLog.getId(),
                orderLog.getOrder().getId(),
                orderLog.getData(),
                DateFormatHelper.dateToString(orderLog.getCreatedAt()),
                DateFormatHelper.dateToString(orderLog.getUpdatedAt())
        );
    }

    public OrderLog createStatusLog(Order order, String status, String description) {
        return new OrderLog(
                order,
                String.format("%s - %s", status, description)
        );
    }
}
