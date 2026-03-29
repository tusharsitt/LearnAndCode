public class WeatherReporter {
    private final WeatherDataSource dataSource;

    // Dependency Inversion: We depend on the interface, not a concrete class
    public WeatherReporter(WeatherDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void displayReport() {
        double temp = dataSource.getTemperature();
        String condition = dataSource.getCondition();

        System.out.println("--- WEATHER REPORT ---");
        System.out.println("Current Temperature: " + temp + "°C");
        System.out.println("Condition: " + condition);
        System.out.println("----------------------\n");
    }
}