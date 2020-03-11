package com.oy.shop.sell.service.impl;

import com.oy.shop.sell.converter.OrderMaster2OrderDTOConverter;
import com.oy.shop.sell.dto.CartDTO;
import com.oy.shop.sell.dto.OrderDTO;
import com.oy.shop.sell.entity.OrderDetail;
import com.oy.shop.sell.entity.OrderMaster;
import com.oy.shop.sell.entity.ProductInfo;
import com.oy.shop.sell.enums.OrderStatusEnum;
import com.oy.shop.sell.enums.PayStatusEnum;
import com.oy.shop.sell.enums.ResultEnum;
import com.oy.shop.sell.exception.SellException;
import com.oy.shop.sell.repository.OrderDetailRepository;
import com.oy.shop.sell.repository.OrderMasterRepository;
import com.oy.shop.sell.service.OrderService;
import com.oy.shop.sell.service.ProductService;
import com.oy.shop.sell.controller.WebSocket;
import com.oy.shop.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/12 8:03
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderMasterRepository orderMasterRepository;

    private ProductService productService;

    private OrderDetailRepository orderDetailRepository;

    private WebSocket webSocket;

    @Autowired
    public OrderServiceImpl(ProductService productService
            , OrderDetailRepository orderDetailRepository
            , OrderMasterRepository orderMasterRepository
            , WebSocket webSocket) {
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderMasterRepository = orderMasterRepository;
        this.webSocket = webSocket;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKeyStr();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                //测试返回状态码也更改的情况
                //throw new ResponseBankException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价int amount = sum + amount;
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3.写入订单详情数据库
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKeyStr());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单数据库(orderMaster、orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> list = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(list);

        //5.发送websocket消息，用户端下单触发这块代码
        webSocket.sendMessage(orderMaster.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Optional<OrderMaster> entity = orderMasterRepository.findById(orderId);
        if (!entity.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(entity.get(), orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("[取消订单] 订单状态不正确,orderId={},orderStatus={}"
                    , orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态,只需要更新一个字段
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[取消订单] 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.写回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单] 订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        /**
         * List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
         *                 .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
         *                 .collect(Collectors.toList());
         * List<想要的对象> list = 已有对象.stream().map(e->想要的对象).collect(Collectors.toList());
         */
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //4.如果用户已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TUDO
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完结订单] 订单状态不正确，orderId={}，orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster update = orderMasterRepository.save(orderMaster);
        if (update == null) {
            log.error("[完结订单] 订单更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[支付订单] 订单状态不正确，orderId={}，orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[支付订单] 订单支付状态不正确，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //2.修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster update = orderMasterRepository.save(orderMaster);
        if (update == null) {
            log.error("[支付订单] 支付订单失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    public List<OrderDTO> findListByOrderStatus(OrderDTO orderDTO) {
        List<OrderMaster> orderMasterList = orderMasterRepository.findByOrderStatus(orderDTO.getOrderStatus());
        List<OrderDTO> convert = OrderMaster2OrderDTOConverter.convert(orderMasterList);
        for (OrderDTO order : convert) {
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(order.getOrderId());
            order.setOrderDetailList(orderDetailList);
        }
        return convert;
    }
}
