package com.ashish.dms.service;

import com.ashish.dms.model.DeliveryPerson;
import com.ashish.dms.model.Order;
import com.ashish.dms.request.AssignOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class DeliveryPersonService {

    @Autowired
    private RestaurantService restaurantService;

    private List<DeliveryPerson> deliveryPeople = new CopyOnWriteArrayList<DeliveryPerson>();

    public List<DeliveryPerson> addDeliveryPerson() {
        // assuming there are 5 deivery persons at initial and all have ACTIVE status
        deliveryPeople.addAll(Arrays.asList(new DeliveryPerson(1, DeliveryPerson.Status.ACTIVE), new DeliveryPerson(2, DeliveryPerson.Status.ACTIVE),
                new DeliveryPerson(3, DeliveryPerson.Status.ACTIVE), new DeliveryPerson(4, DeliveryPerson.Status.ACTIVE),
                new DeliveryPerson(5, DeliveryPerson.Status.ACTIVE)));
        return deliveryPeople;
    }

    List<DeliveryPerson> getDeliveryPersonList() {
        return deliveryPeople;
    }

    private DeliveryPerson getDeliveryPersonById(int delhiveryPersonId) {
        Optional<DeliveryPerson> optional = deliveryPeople.stream().filter(delhiveryPerson -> delhiveryPerson.getId() == delhiveryPersonId).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public String assignOrder(AssignOrderRequest assignOrderRequest) {
        Order order = restaurantService.getOrderById(assignOrderRequest.getOrderId());
        DeliveryPerson deliveryPerson = getDeliveryPersonById(assignOrderRequest.getDeliveryPersonId());
        //different conditions to assign the order
        if (order == null) {
            return "No order found";
        } else if (deliveryPerson == null) {
            return "No Delivery Person Found";
        } else if (deliveryPerson.getOrderId() != -1) {
            return "Delivery Person is already assigned to an order";
        } else {
            deliveryPerson.setOrderId(assignOrderRequest.getOrderId());
            return "Accepted";
        }
    }

    public DeliveryPerson.Status getDeliveryPersonStatus(int delhiveryPersonId) {
        DeliveryPerson deliveryPerson = getDeliveryPersonById(delhiveryPersonId);
        return deliveryPerson != null ? deliveryPerson.getStatus() : null;
    }

}
