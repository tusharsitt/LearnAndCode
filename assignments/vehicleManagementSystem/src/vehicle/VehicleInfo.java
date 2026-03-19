package vehicle;

public class VehicleInfo {

    private final String make;
    private final String model;
    private final  int yearOfManufacture;
    private double price;

    public VehicleInfo(String make, String model, int yearOfManufacture) {
        this( make, model, yearOfManufacture, 0.0);
    }

    public VehicleInfo(String make, String model, int yearOfManufacture, double price) {
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.price = price;
    }

    public String getMake() {
        return make;
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

    public void updatePrice(double newPrice) throws IllegalArgumentException{

        if(newPrice < 0){
            throw new  IllegalArgumentException("Price cannot be negative");
        }
        this.price = newPrice;
    }

}
