package vehicle.car;

import vehicle.AbstractVehicle;
import vehicle.VehicleInfo;

public class Car extends AbstractVehicle {

    public Car(VehicleInfo vehicleInfo) {
        super(vehicleInfo);
    }

    @Override
    public void printDetails() {
        System.out.println("Car: " + getVehicleInfo().getYearOfManufacture() + " " +
                getVehicleInfo().getBrand() + " " + getVehicleInfo().getModel() +
                ", Price: $" + getVehicleInfo().getPrice());
    }
}