package vehicle;

public interface Vehicle {
    void start();
    void stop();
    void replenishEnergy(double amount);
    void printDetails();
    double getPrice();
    void updatePrice(double newPrice);
}

