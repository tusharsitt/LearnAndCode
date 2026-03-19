package vehicle.car;

import vehicle.AbstractVehicle;
import vehicle.VehicleInfo;

public class Car extends AbstractVehicle {

    public Car(VehicleInfo vehicleInfo) {
        super(vehicleInfo, 60.0); // 60 liters fuel capacity
    }

    @Override
    public void printDetails() {
        System.out.println("Car: " + getVehicleInfo().getYearOfManufacture() + " " +
                getVehicleInfo().getMake() + " " + getVehicleInfo().getModel() +
                ", Price: $" + getVehicleInfo().getPrice());
    }
}