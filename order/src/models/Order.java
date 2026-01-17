package models;

import java.util.List;

public class Order {

    String id;
    String transactionId;
    String customerId;
    OrderStatus status;
    List<String> items;
    int totalAmount;

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<String> getItems() {
        return items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Object getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
