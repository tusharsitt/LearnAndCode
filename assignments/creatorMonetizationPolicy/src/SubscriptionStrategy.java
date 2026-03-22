public class SubscriptionStrategy implements EarningStrategy {
    private static final double SUBSCRIPTION_FEE = 2.0;

    @Override
    public double calculateEarning(MonetizationContext context) {
        return context.subscribers() * SUBSCRIPTION_FEE;
    }
}