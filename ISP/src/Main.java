public class Main {
    public static void main(String[] args) {
        SmartHomeManager manager = new SmartHomeManager();

        BasicLight kitchenLight = new BasicLight();
        SmartBulb livingRoomBulb = new SmartBulb();
        Thermostat thermostat = new Thermostat();

        manager.registerDevice(kitchenLight);
        manager.registerDevice(livingRoomBulb);
        manager.registerDevice(thermostat);

        livingRoomBulb.setBrightness(50);
        thermostat.setTemperature(22.5);

        manager.nightMode();
    }
}