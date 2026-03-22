package vehicle.car;

import vehicle.AbstractVehicle;
import vehicle.VehicleInfo;

public class ElectricCar extends AbstractVehicle {

    public ElectricCar(VehicleInfo vehicleInfo) {
        super(vehicleInfo);
    }

    @Override
    public void printDetails() {
        System.out.println("Electric Car: " + getVehicleInfo().getYearOfManufacture() + " " +
                getVehicleInfo().getBrand() + " " + getVehicleInfo().getModel() +
                ", Price: $" + getVehicleInfo().getPrice());
    }

    public double getBatteryLevel() {
        return getEnergyLevel();
    }
}