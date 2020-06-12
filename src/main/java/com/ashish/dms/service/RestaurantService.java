package com.ashish.dms.service;

import com.ashish.dms.request.ChangeOrderStatusRequest;
import com.ashish.dms.model.DeliveryPerson;
import com.ashish.dms.model.Item;
import com.ashish.dms.model.Order;
import com.ashish.dms.request.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class RestaurantService {

    @Autowired
    private DeliveryPersonService deliveryPersonService;

    private List<Order> orders       = new CopyOnWriteArrayList<Order>();
    private int         orderIdCount = 1;

    public Order placeOrder(PlaceOrderRequest placeOrderRequest) {
        Order order = new Order();
        order.setId(orderIdCount++);
        order.setItemId(placeOrderRequest.getItemId());
        order.setNumberOfItems(placeOrderRequest.getNoOfItems());
        order.setStatus(Order.Status.CREATED);
        orders.add(order);
        return order;
    }

    Order getOrderById(int orderId) {
        Optional<Order> orderOptional = orders.stream().filter(order -> order.getId() == orderId).findFirst();
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            return null;
        }
    }

    public Order.Status getOrderStatus(int orderId) {
        Optional<Order> orderOptional = orders.stream().filter(order -> order.getId() == orderId).findFirst();
        if (orderOptional.isPresent()) {
            return orderOptional.get().getStatus();
        } else {
            return null;
        }
    }

    public Order.Status updateOrderStatus(ChangeOrderStatusRequest changeOrderStatusRequest) {
        Order order = getOrderById(changeOrderStatusRequest.getOrderId());
        order.setStatus(Order.Status.valueOf(changeOrderStatusRequest.getStatus()));
        return order.getStatus();
    }

    public List<DeliveryPerson> getActiveDeliveryPersons() {
        List<DeliveryPerson> deliveryPeople = deliveryPersonService.getDeliveryPersonList();
        return deliveryPeople.stream().filter(delhiveryPerson -> delhiveryPerson.getStatus().equals(DeliveryPerson.Status.ACTIVE)).collect(
                Collectors.toList());
    }
}
