package com.ashish.dms.api;

import com.ashish.dms.model.DeliveryPerson;
import com.ashish.dms.request.AssignOrderRequest;
import com.ashish.dms.service.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryPersonContoller {
    @Autowired
    private DeliveryPersonService deliveryPersonService;

    @GetMapping(value = "/delhiveryperson/add")
    public List<DeliveryPerson> addDelhiveryPerson() {
        return deliveryPersonService.addDeliveryPerson();
    }

    @RequestMapping( value = "/order/assign")
    @PostMapping
    public String assignOrder(@RequestBody AssignOrderRequest assignOrderRequest) {
        return deliveryPersonService.assignOrder(assignOrderRequest);
    }


    @GetMapping(value = "/delhiveryPerson/getStatus/{delhiveryPersonId}")
    public DeliveryPerson.Status assignOrder(@PathVariable("delhiveryPersonId") int delhiveryPersonId) {
        return deliveryPersonService.getDeliveryPersonStatus(delhiveryPersonId);
    }


}
