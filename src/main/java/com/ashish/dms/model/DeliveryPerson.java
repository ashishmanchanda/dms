package com.ashish.dms.model;

public class DeliveryPerson {

    public static enum Status {
        ACTIVE,
        INACTIVE
    }

    private int    id;
    private int    orderId=-1;
    private DeliveryPerson.Status status;

    public DeliveryPerson(int id,DeliveryPerson.Status status) {
        this.id = id;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
