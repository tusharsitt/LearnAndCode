public class BrandDealStrategy implements EarningStrategy {
    private final double baseContractAmount;

    public BrandDealStrategy(double baseContractAmount) {
        this.baseContractAmount = baseContractAmount;
    }

    @Override
    public double calculateEarning(MonetizationContext context) {
        if (context.season() == Season.HOLIDAY && context.engagementRate() > 0.05) {
            return baseContractAmount * 1.2;
        }
        return baseContractAmount;
    }
}