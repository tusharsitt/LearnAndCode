import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<WeatherReporter> reporters = new ArrayList<>();

        reporters.add(new WeatherReporter(new ApiWeatherSource()));
        reporters.add(new WeatherReporter(new DatabaseWeatherSource()));

        for (WeatherReporter reporter : reporters) {
            reporter.displayReport();
        }
    }
}