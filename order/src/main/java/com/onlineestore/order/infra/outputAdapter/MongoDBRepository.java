package com.onlineestore.order.infra.outputAdapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.onlineestore.order.domain.Order;
import com.onlineestore.order.infra.outputport.IOrderRepository;

@Repository
public class MongoDBRepository implements IOrderRepository {

    @Autowired
    private MongoTemplate mt;

    @Override
    public Order save(Order order) {
        return mt.save(order);
    }

    @Override
    public Order getById(String id) {
        return mt.findById(id, Order.class);
    }

    @Override
    public List<Order> getAll() {
        return mt.findAll(Order.class);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mt.findAndRemove(query, Order.class);
    }

}
