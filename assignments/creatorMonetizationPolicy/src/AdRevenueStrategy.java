public class AdRevenueStrategy implements EarningStrategy {
    private static final double BASE_CPM = 0.05;

    @Override
    public double calculateEarning(MonetizationContext context) {
        double multiplier = getRegionMultiplier(context.region());
        return context.views() * BASE_CPM * multiplier;
    }

    private double getRegionMultiplier(Region region) {
     return  switch (region) {
         case US -> 1.5;
         case UK -> 1.2;
         case IN -> 0.8;
         case GLOBAL -> 1.0;
        };
    }
}