import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        CountryCodeRegistry registry = CountryCodeRegistry.getInstance();
        CountryLookupApp app = new CountryLookupApp(registry);

        app.start();

    }
}