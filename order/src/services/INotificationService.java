package services;

import models.Order;

public interface INotificationService {
    void sendConfirmation(Order order);
}
