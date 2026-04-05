public class DeviceSuspendedException extends RuntimeException {
    public DeviceSuspendedException() {
        super("Device is suspended");
    }
}