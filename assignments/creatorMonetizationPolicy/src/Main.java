public class Main {
    public static void main(String[] args) {
        Creator creator = new Creator("TechGuru");

        creator.addRevenueStream(new AdRevenueStrategy());
        creator.addRevenueStream(new SubscriptionStrategy());
        creator.addRevenueStream(new BrandDealStrategy(5000.00));

        MonetizationContext thisMonthContext = new MonetizationContext(
                100000,
                5000,
                0.08,
                Region.US,
                Season.HOLIDAY
        );

        double totalPayout = creator.calculateTotalEarnings(thisMonthContext);
        System.out.println(creator.getName() + " earned: $" + totalPayout);
    }
}