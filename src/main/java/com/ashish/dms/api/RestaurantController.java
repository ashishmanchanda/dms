package com.ashish.dms.api;

import com.ashish.dms.request.ChangeOrderStatusRequest;
import com.ashish.dms.model.DeliveryPerson;
import com.ashish.dms.model.Item;
import com.ashish.dms.model.Order;
import com.ashish.dms.request.PlaceOrderRequest;
import com.ashish.dms.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/item/add")
    public List<Item> addItem() {
        //assuming 5 items at initial
        return Arrays.asList(new Item(1, "test1"), new Item(2, "test2"), new Item(3, "test3"), new Item(4, "test4"), new Item(5, "test5")
        );
    }

    @PostMapping( value = "/order/place")
    public Order placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest) {
        return restaurantService.placeOrder(placeOrderRequest);
    }

    @GetMapping(value = "/order/getStatus/{orderId}")
    public Order.Status getOrderStatus(@PathVariable("orderId") int orderId) {
        return restaurantService.getOrderStatus(orderId);
    }

    @PostMapping( value = "/order/updateStatus")
    public Order.Status updateOrderStatus(@RequestBody ChangeOrderStatusRequest changeOrderStatusRequest) {
        return restaurantService.updateOrderStatus(changeOrderStatusRequest);
    }

    @GetMapping( value = "/getAllActiveDeliveryPersons")
    public List<DeliveryPerson> getActiveDeliveryPersons() {
        return restaurantService.getActiveDeliveryPersons();
    }


}

