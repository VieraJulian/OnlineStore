package com.onlineestore.order.infra.inputport;

import java.util.List;

import com.onlineestore.order.infra.dto.OrderDTO;
import com.onlineestore.order.infra.dto.OrderInfo;

public interface IOrderInputPort {

    public OrderInfo createOrder(OrderDTO order);

    public OrderInfo getOrderById(String id);

    public List<OrderDTO> getAllOrders();

    public String deleteOrder(String id);
}
