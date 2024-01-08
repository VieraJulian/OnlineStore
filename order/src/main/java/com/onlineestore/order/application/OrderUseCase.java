package com.onlineestore.order.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineestore.order.domain.Order;
import com.onlineestore.order.infra.dto.OrderDTO;
import com.onlineestore.order.infra.dto.OrderInfo;
import com.onlineestore.order.infra.dto.ProductDTO;
import com.onlineestore.order.infra.dto.UserDTO;
import com.onlineestore.order.infra.inputport.IOrderInputPort;
import com.onlineestore.order.infra.outputport.IOrderRepository;
import com.onlineestore.order.infra.outputport.IProductServicePort;
import com.onlineestore.order.infra.outputport.IUserServicePort;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderUseCase implements IOrderInputPort {

    @Autowired
    private IOrderRepository repo;

    @Autowired
    private IProductServicePort prodServ;

    @Autowired
    private IUserServicePort userServ;

    @Override
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackGetUserProduct")
    public OrderInfo createOrder(OrderDTO order) {
        Order getOrder = Order.builder()
                .user_id(order.getUser_id())
                .product_id(order.getProduct_id())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();

        prodServ.setStock(order.getProduct_id(), order.getQuantity());

        Order orderNew = repo.save(getOrder);

        ProductDTO product = prodServ.getProduct(orderNew.getProduct_id());
        UserDTO user = userServ.getUser(orderNew.getUser_id());

        return OrderInfo.builder()
                .id(orderNew.getId())
                .user(user)
                .product(product)
                .price(orderNew.getPrice())
                .quantity(orderNew.getQuantity())
                .build();
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackGetUserProduct")
    public OrderInfo getOrderById(String id) {
        Order order = repo.getById(id);

        ProductDTO product = prodServ.getProduct(order.getProduct_id());
        UserDTO user = userServ.getUser(order.getUser_id());

        return OrderInfo.builder()
                .id(order.getId())
                .user(user)
                .product(product)
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orders = new ArrayList<>();

        List<Order> ordersDB = repo.getAll();

        for (Order order : ordersDB) {
            OrderDTO or = OrderDTO.builder()
                    .id(order.getId())
                    .user_id(order.getUser_id())
                    .product_id(order.getProduct_id())
                    .price(order.getPrice())
                    .quantity(order.getQuantity())
                    .build();

            orders.add(or);
        }

        return orders;

    }

    @Override
    public String deleteOrder(String id) {
        repo.delete(id);

        return "Order deleted successfully!";
    }

    public OrderInfo fallbackGetUserProduct(Throwable throwable) {
        return OrderInfo.builder()
                .id(null)
                .user(null)
                .product(null)
                .price(null)
                .quantity(0)
                .build();
    }
}
