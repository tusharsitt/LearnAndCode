public record MonetizationContext(
        int views,
        int subscribers,
        double engagementRate,
        Region region,
        Season season
) {
    public MonetizationContext {
        if (views < 0 || subscribers < 0) {
            throw new IllegalArgumentException("Views and subscribers cannot be negative.");
        }
        if (engagementRate < 0.0 || engagementRate > 1.0) {
            throw new IllegalArgumentException("Engagement rate must be between 0.0 and 1.0");
        }
    }

}