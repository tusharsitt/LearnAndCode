package vehicle.motorcycle;

import vehicle.AbstractVehicle;
import vehicle.VehicleInfo;

public class Motorcycle extends AbstractVehicle {

    private final boolean hasSidecar;

    public Motorcycle(VehicleInfo vehicleInfo, boolean hasSidecar) {
        super(vehicleInfo);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void printDetails() {
        System.out.println("Motorcycle: " + getVehicleInfo().getYearOfManufacture() + " " +
                getVehicleInfo().getMake() + " " + getVehicleInfo().getModel() +
                ", Sidecar: " + hasSidecar +
                ", Price: $" + getVehicleInfo().getPrice());
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }
}