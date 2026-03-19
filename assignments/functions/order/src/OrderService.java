
interface OrderRepository{

    void save(int orderId, double finalAmount);
}

class OrderService {

    private static final double TAX_RATE = 0.18;
    private static final double DISCOUNT_RATE = 0.10;

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(int orderId, double amount) {
        validateOrder(orderId, amount);
        double finalAmount = calculateFinalAmount(amount);
        orderRepository.save(orderId, finalAmount);
    }

    private void validateOrder(int orderId, double amount) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Invalid Order ID: " + orderId);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Order amount cannot be empty or negative");
        }
    }

    private double calculateFinalAmount(double amount) {
        double discount = calculateDiscount(amount);
        double tax = calculateTax(amount);
        return (amount + tax) - discount;
    }

    private double calculateDiscount(double amount) {
        return amount * DISCOUNT_RATE;
    }

    private double calculateTax(double amount) {
        return amount * TAX_RATE;
    }

}