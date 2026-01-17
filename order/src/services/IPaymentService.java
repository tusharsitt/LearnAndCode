package services;

import exceptions.PaymentFailedException;
import models.Order;

public interface IPaymentService {
    void refundPayment(Object transactionId);

    String processPayment(Order order) throws PaymentFailedException;
}
