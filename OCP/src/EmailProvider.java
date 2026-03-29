public class EmailProvider implements NotificationProvider
{
    @Override
    public void send(NotificationMessage message) {
        System.out.println("Email sent to " + message.recipient() + ": " + message.content());
    }
}
