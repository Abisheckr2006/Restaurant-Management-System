package com.rest.models;

public class Order {
    public enum OrderStatus {
        PENDING, PREPARING, READY, SERVED, PAID
    }

    private int id;
    private int tableId;
    private String itemName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private OrderStatus status;

    public Order(int id, int tableId, String itemName, int quantity, double unitPrice) {
        this.id = id;
        this.tableId = tableId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = quantity * unitPrice;
        this.status = OrderStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.quantity * this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.totalPrice = this.quantity * this.unitPrice;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void updateTotalPrice() {
        this.totalPrice = this.quantity * this.unitPrice;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", tableId=" + tableId + ", itemName='" + itemName + '\''
                + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice
                + ", status=" + status + "}";
    }
}
