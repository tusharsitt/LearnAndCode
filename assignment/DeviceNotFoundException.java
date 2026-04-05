public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() {
        super("Invalid device handle");
    }
}