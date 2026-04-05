public class NetworkConnectionException extends RuntimeException {
    public NetworkConnectionException() {
        super("WiFi not connected");
    }
}