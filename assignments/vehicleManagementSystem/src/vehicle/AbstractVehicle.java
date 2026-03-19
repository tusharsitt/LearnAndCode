package vehicle;

public abstract class AbstractVehicle implements Vehicle {

    private final VehicleInfo vehicleInfo;
    private double energyLevel;
    private boolean isRunning;

    public AbstractVehicle(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
        this.energyLevel = 0.0;
        this.isRunning = false;
    }

    @Override
    public double getPrice() {
        return vehicleInfo.getPrice();
    }

    @Override
    public void updatePrice(double newPrice) {
        this.vehicleInfo.updatePrice(newPrice);
    }

    @Override
    public void start() {
        if (energyLevel > 0) {
            isRunning = true;
            System.out.println(vehicleInfo.getMake() + " " + vehicleInfo.getModel() + " started.");
        } else {
            System.out.println("Cannot start " + vehicleInfo.getModel() + " - out of energy!");
        }
    }

    @Override
    public void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println(vehicleInfo.getMake() + " " + vehicleInfo.getModel() + " stopped.");
        } else {
            System.out.println("The " + vehicleInfo.getModel() + " is already stopped.");
        }
    }

    @Override
    public void replenishEnergy(double amount) {
        if (amount < 0 || energyLevel + amount > vehicleInfo.getMaxEnergyCapacity()) {
            System.out.println("Invalid energy amount. Max capacity is " + vehicleInfo.getMaxEnergyCapacity());
            return;
        }
        energyLevel += amount;
        System.out.println(vehicleInfo.getModel() + " energy replenished. Current level: " + energyLevel);
    }

    protected VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    protected double getEnergyLevel() {
        return energyLevel;
    }

    @Override
    public abstract void printDetails();
}