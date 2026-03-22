import java.util.HashSet;
import java.util.Set;

public class Creator {
    private final String name;

    private final Set<EarningStrategy> revenueStreams;;

    public Creator(String name) {
        this.name = name;
        this.revenueStreams = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addRevenueStream(EarningStrategy strategy) {
        this.revenueStreams.add(strategy);
    }

    public double calculateTotalEarnings(MonetizationContext context) {
        double totalEarnings = 0;
        for (EarningStrategy strategy : revenueStreams) {
            totalEarnings += strategy.calculateEarning(context);
        }
        return totalEarnings;
    }
}