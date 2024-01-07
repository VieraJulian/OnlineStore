package com.onlineestore.order.infra.inputportAdapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineestore.order.infra.dto.OrderDTO;
import com.onlineestore.order.infra.dto.OrderInfo;
import com.onlineestore.order.infra.inputport.IOrderInputPort;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private IOrderInputPort orderInputPort;

    @PostMapping("/create")
    public ResponseEntity<OrderInfo> createOrder(@RequestBody OrderDTO order) {
        try {
            OrderInfo orderInfo = orderInputPort.createOrder(order);

            return new ResponseEntity<>(orderInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<OrderInfo> getOrderById(@PathVariable String id) {
        try {
            OrderInfo orderInfo = orderInputPort.getOrderById(id);

            return new ResponseEntity<>(orderInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            List<OrderDTO> orders = orderInputPort.getAllOrders();

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        try {
            String msj = orderInputPort.deleteOrder(id);

            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
