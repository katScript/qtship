package com.spring.app.orders.services;

import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.payload.OrderStatusData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusService {
    public static final String TRANSFER_SHIPPER = "TRANSFER_SHIPPER";
    public static final String SHIPPER_CONFIRMED = "SHIPPER_CONFIRMED";

    public OrderStatusData processOrderStatusData(OrderStatus orderStatus) {
        List<String> child = new ArrayList<>();

        for (OrderStatus os: orderStatus.getChild()) {
            child.add(os.getCode());
        }

        OrderStatusData orderStatusData = new OrderStatusData(
                orderStatus.getId(),
                orderStatus.getCode(),
                orderStatus.getLabel(),
                orderStatus.getEnable(),
                child
        );

        orderStatusData.setCreatedAt(
                DateFormatHelper.dateToString(orderStatus.getCreatedAt())
        );
        orderStatusData.setUpdatedAt(
                DateFormatHelper.dateToString(orderStatus.getUpdatedAt())
        );

        return orderStatusData;
    }
}
