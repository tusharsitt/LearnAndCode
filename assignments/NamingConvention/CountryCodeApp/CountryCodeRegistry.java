
import java.util.HashMap;


public class CountryCodeRegistry {

    private static CountryCodeRegistry registry;
    private final HashMap<String, String> countryCodeMap;

    private CountryCodeRegistry() {
        countryCodeMap = new HashMap<>();

        countryCodeMap.put("US", "United States");
        countryCodeMap.put("IN", "India");
        countryCodeMap.put("GB", "United Kingdom");
        countryCodeMap.put("CA", "Canada");
        countryCodeMap.put("AU", "Australia");
        countryCodeMap.put("DE", "Germany");
        countryCodeMap.put("FR", "France");
        countryCodeMap.put("IT", "Italy");
        countryCodeMap.put("ES", "Spain");
        countryCodeMap.put("NL", "Netherlands");
        countryCodeMap.put("SE", "Sweden");
        countryCodeMap.put("CH", "Switzerland");
        countryCodeMap.put("PL", "Poland");

    }

    public static CountryCodeRegistry getInstance() {

        if (registry == null) {
            registry = new CountryCodeRegistry();
        }

        return registry;
    }

    public String getCountryName(String code) {
        return countryCodeMap.get(code);
    }
   
}