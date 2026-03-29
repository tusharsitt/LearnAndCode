public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        service.addProvider(new EmailProvider());
        service.addProvider(new SmsProvider());

        NotificationMessage msg = new NotificationMessage("User123", "Your order has been shipped!");

        System.out.println("--- Starting Notifications ---");
        service.notifyAll(msg);

    }
}