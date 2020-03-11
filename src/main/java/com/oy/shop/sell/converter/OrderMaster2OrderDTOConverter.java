package com.oy.shop.sell.converter;

import com.oy.shop.sell.dto.OrderDTO;
import com.oy.shop.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: orderMaster转换为OrderDTO
 * @Author: feixi
 * @Date: 2020/1/12 19:17
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList = orderMasterList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
        return orderDTOList;
    }
}
