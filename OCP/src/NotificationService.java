import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<NotificationProvider> providers = new ArrayList<>();

    public void addProvider(NotificationProvider provider) {
        providers.add(provider);
    }

    public void notifyAll(NotificationMessage message) {
        for (NotificationProvider provider : providers) {
            provider.send(message);
        }
    }
}