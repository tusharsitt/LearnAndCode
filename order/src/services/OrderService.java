package services;

import exceptions.OrderException;
import exceptions.PaymentFailedException;
import models.Order;
import models.OrderStatus;
import repositories.OrderRepository;

public class OrderService {

    private final IInventoryService inventoryService;
    private final INotificationService notificationService;
    private final IPaymentService paymentService;
    private final OrderRepository orderRepository;

    public OrderService(
            IInventoryService inventoryService,
            INotificationService notificationService,
            IPaymentService paymentService,
            OrderRepository orderRepository) {
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
    }

    public void placeOrder(Order order) throws OrderException {
        validateOrder(order);
        verifyInventory(order);
        inventoryService.reserveItems(order.getItems());
        String transactionId = executeTransaction(order);
        finalizeOrder(order, transactionId);
    }

    private void validateOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("Order cannot be null");
        }

        // TODO: Update validation to include checks for international shipping constraints
        if(order.getItems().isEmpty() || order.getTotalAmount() < 0) {
            throw new OrderException("Order validation failed");
        }
    }

    private void verifyInventory(Order order) {
        if (!inventoryService.isAvailable(order.getItems())) {
            throw new OrderException("Insufficient inventory");
        }
    }

    private String executeTransaction(Order order) {
        try {
            return paymentService.processPayment(order);
        } catch (PaymentFailedException e) {
            // Rollback Logic
            inventoryService.releaseReservation(order.getItems());
            throw new OrderException("Payment failed: " + e.getMessage());
        }
    }

    private void finalizeOrder(Order order, String transactionId) {
        order.setTransactionId(transactionId);
        inventoryService.commitReservation(order.getItems());
        notificationService.sendConfirmation(order);
    }

    public void cancelOrder(String orderId){
        Order orderToCancel = orderRepository.getById(orderId);
        if (isEligibleForRefund(orderToCancel)) {
            processRefund(orderToCancel);
        }
        markAsCancelled(orderToCancel);
    }

    private boolean isEligibleForRefund(Order order) {
        return order.getStatus() == OrderStatus.PAID;
    }

    private void processRefund(Order order) {
        paymentService.refundPayment(order.getTransactionId());
        inventoryService.restoreInventory(order.getItems());
    }

    private void markAsCancelled(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

}
