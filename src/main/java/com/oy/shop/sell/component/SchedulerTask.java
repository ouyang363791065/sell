package com.oy.shop.sell.component;


import com.oy.shop.sell.dto.OrderDTO;
import com.oy.shop.sell.enums.OrderStatusEnum;
import com.oy.shop.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/23 21:03
 */
@Component
@Slf4j
public class SchedulerTask {
    private OrderService orderService;

    @Autowired
    public SchedulerTask(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        //查询所有新下单状态的订单
        List<OrderDTO> newOrderList = orderService.findListByOrderStatus(orderDTO);
        //获取当前时间
        OffsetDateTime now = OffsetDateTime.now();
        for (OrderDTO order : newOrderList) {
            Instant instant = order.getCreateTime().toInstant();
            OffsetDateTime odt = instant.atOffset(ZoneOffset.UTC);
            if ( ChronoUnit.SECONDS.between(odt, now) > 15 * 60) {
                //超过15分钟对订单进行取消
                OrderDTO cancel = orderService.cancel(order);
            }
        }
    }
}
