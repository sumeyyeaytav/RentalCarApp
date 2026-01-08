
import java.util.ArrayList;

public class RentalManager {
    public ArrayList<Car> carList;

    public RentalManager() {
        carList = new ArrayList<>();
    }

    public void addCar(Car car) {
        carList.add(car);
    }
}