
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CountryCodeRegistry {

    private static CountryCodeRegistry registry;
    private final Map<String, CountryInfo> countryCodeMap;


    private CountryCodeRegistry() {
        countryCodeMap = new HashMap<>();

        countryCodeMap.put("CA", new CountryInfo("Canada", List.of("United States")));
        countryCodeMap.put("MX", new CountryInfo("Mexico", List.of("United States", "Guatemala", "Belize")));
        countryCodeMap.put("BR", new CountryInfo("Brazil", List.of("Argentina", "Bolivia", "Colombia", "Paraguay", "Peru", "Uruguay", "Venezuela")));
        countryCodeMap.put("AR", new CountryInfo("Argentina", List.of("Bolivia", "Brazil", "Chile", "Paraguay", "Uruguay")));
        countryCodeMap.put("CL", new CountryInfo("Chile", List.of("Argentina", "Bolivia", "Peru")));
        countryCodeMap.put("CO", new CountryInfo("Colombia", List.of("Brazil", "Ecuador", "Panama", "Peru", "Venezuela")));
        countryCodeMap.put("PE", new CountryInfo("Peru", List.of("Bolivia", "Brazil", "Chile", "Colombia", "Ecuador")));
        countryCodeMap.put("CN", new CountryInfo("China", List.of("Afghanistan", "Bhutan", "India", "Kazakhstan", "Kyrgyzstan", "Laos", "Mongolia", "Myanmar", "Nepal", "North Korea", "Pakistan", "Russia", "Tajikistan", "Vietnam")));
        countryCodeMap.put("KR", new CountryInfo("South Korea", List.of("North Korea")));
        countryCodeMap.put("KP", new CountryInfo("North Korea", List.of("China", "Russia", "South Korea")));
        countryCodeMap.put("IN", new CountryInfo("India", List.of("Bangladesh", "Bhutan", "China", "Myanmar", "Nepal", "Pakistan")));
        countryCodeMap.put("PK", new CountryInfo("Pakistan", List.of("Afghanistan", "China", "India", "Iran")));
        countryCodeMap.put("ID", new CountryInfo("Indonesia", List.of("Papua New Guinea", "Malaysia", "Timor-Leste")));
        countryCodeMap.put("TH", new CountryInfo("Thailand", List.of("Cambodia", "Laos", "Malaysia", "Myanmar")));
        countryCodeMap.put("VN", new CountryInfo("Vietnam", List.of("Cambodia", "China", "Laos")));
        countryCodeMap.put("MY", new CountryInfo("Malaysia", List.of("Brunei", "Indonesia", "Thailand")));

    }

    public static CountryCodeRegistry getInstance() {

        if (registry == null) {
            registry = new CountryCodeRegistry();
        }

        return registry;
    }

    public CountryInfo getCountry(String code) {
        return countryCodeMap.get(code);
    }
   
}