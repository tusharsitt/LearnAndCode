import java.util.ArrayList;
import java.util.List;

public class SmartHomeManager {
    private final List<Switchable> basicDevices = new ArrayList<>();
    private final List<IntensityAdjustable> intensityControllables = new ArrayList<>();

    public void registerDevice(Switchable device) {

        basicDevices.add(device);

        if (device instanceof IntensityAdjustable) {
            intensityControllables.add((IntensityAdjustable) device);
        }

    }

    public void nightMode() {
        System.out.println("--- Activating Night Mode ---");
        for (Switchable device : basicDevices) {
            device.turnOff();
        }
    }
}