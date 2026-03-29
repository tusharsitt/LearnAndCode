public class Thermostat implements Switchable, TemperatureControllable {
    @Override
    public void turnOn() { System.out.println("Thermostat system ON"); }
    @Override
    public void turnOff() { System.out.println("Thermostat system OFF"); }
    @Override
    public void setTemperature(double temp) {
        System.out.println("Temperature set to " + temp + "°C");
    }
}