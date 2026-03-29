public class DatabaseWeatherSource implements WeatherDataSource {
    @Override
    public double getTemperature() {
        // SQL query logic here
        return 22.0;
    }

    @Override
    public String getCondition() {
        // SQL query logic here too
        return "Cloudy";
    }
}