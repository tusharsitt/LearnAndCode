public class ApiWeatherSource implements WeatherDataSource {
    @Override
    public double getTemperature() {
        // Here an API call could be made to fetch temperature
        return 28.5;
    }

    @Override
    public String getCondition() {
        // Here an API call could be made to fetch Condition
        return "Sunny";
    }
}