package vehicle;

public class VehicleInfo {

    private final String brand;
    private final String model;
    private final  int yearOfManufacture;
    private final double maxEnergyCapacity;
    private double price;


    public VehicleInfo(String brand, String model, int yearOfManufacture, double maxEnergyCapacity) {
        this( brand, model, yearOfManufacture,   maxEnergyCapacity, 0.0);
    }

    public VehicleInfo(String brand, String model, int yearOfManufacture, double maxEnergyCapacity, double price) {
        this.brand = brand;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.maxEnergyCapacity = maxEnergyCapacity;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public double getPrice() {
        return price;
    }

    public double getMaxEnergyCapacity() {
        return maxEnergyCapacity;
    }

    public void updatePrice(double newPrice) throws IllegalArgumentException{

        if(newPrice < 0){
            throw new  IllegalArgumentException("Price cannot be negative");
        }
        this.price = newPrice;
    }

}
