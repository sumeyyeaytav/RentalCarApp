public class Car {
    public String brand, model, fuelType, transmission;
    public double dailyPrice;
    public boolean isRented = false;

    // Constructor: Tüm detayları içeren yapıcı metot
    public Car(String brand, String model, double dailyPrice, String fuelType, String transmission) {
        this.brand = brand;
        this.model = model;
        this.dailyPrice = dailyPrice;
        this.fuelType = fuelType;
        this.transmission = transmission;
    }
}