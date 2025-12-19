public class SmartBulb implements Switchable, IntensityAdjustable {

    @Override
    public void turnOn() { System.out.println("Smart Bulb is ON"); }

    @Override
    public void turnOff() { System.out.println("Smart Bulb is OFF"); }

    @Override
    public void setBrightness(int level) {
        System.out.println("Brightness set to " + level + "%");
    }
}