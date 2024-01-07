package com.onlineestore.order.infra.outputport;

import java.util.List;

import com.onlineestore.order.domain.Order;

public interface IOrderRepository {

    public Order save(Order order);

    public Order getById(String id);

    public List<Order> getAll();

    public void delete(String id);
}
