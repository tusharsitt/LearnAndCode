package app;

import vehicle.VehicleInfo;
import vehicle.VehicleManager;
import vehicle.car.Car;
import vehicle.car.ElectricCar;
import vehicle.motorcycle.Motorcycle;

public class Program {
    public static void main(String[] args) {
        System.out.println("=== Refactored Vehicle Management Demo ===\n");

        VehicleInfo hondaInfo = new VehicleInfo("Honda", "Accord", 2023, 28000);
        VehicleInfo harleyInfo = new VehicleInfo("Harley-Davidson", "Street 750", 2022, 7500);
        VehicleInfo teslaInfo = new VehicleInfo("Tesla", "Model 3", 2023, 42000);


        Car car = new Car(hondaInfo);
        Motorcycle motorcycle = new Motorcycle(harleyInfo, false);
        ElectricCar electricCar = new ElectricCar(teslaInfo);

        System.out.println("--- Testing Individual Vehicles ---");
        car.replenishEnergy(100);
        car.start();

        motorcycle.replenishEnergy(80);
        motorcycle.printDetails();

        electricCar.replenishEnergy(100);
        electricCar.start();

        VehicleManager manager = new VehicleManager();

        System.out.println("\n--- Populating Manager ---");
        manager.addVehicle(car);
        manager.addVehicle(motorcycle);
        manager.addVehicle(electricCar);

        manager.displayAll();

        System.out.printf("\nTotal Fleet Value: $%.2f%n", manager.calcTotalValue());

        manager.startAllVehicles();

        System.out.println("\n--- Testing Encapsulation ---");
        try {
            System.out.println("Attempting to set a negative price...");
            car.updatePrice(-1000); // This should throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        System.out.println("\n=== Demo Complete ===");
    }
}
