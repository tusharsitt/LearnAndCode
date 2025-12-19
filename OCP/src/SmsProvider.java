public class SmsProvider implements NotificationProvider
{
    @Override
    public void send(NotificationMessage message) {
        System.out.println("SMS sent to " + message.recipient() + ": " + message.content());
    }
}
