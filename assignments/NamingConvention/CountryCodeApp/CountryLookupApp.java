import java.util.Scanner;

public class CountryLookupApp {

    final private String QUIT_COMMAND = "Q";
    final private CountryCodeRegistry registry;
    final private Scanner scanner;

    public CountryLookupApp(CountryCodeRegistry registry) {
        this.registry = registry;
        this.scanner =  new Scanner(System.in);
    }

    public void start(){

        System.out.println("Welcome to Country Lookup App");

        while(true){
            String inputCode = promptUserForCode();
            if(inputCode.equals(QUIT_COMMAND)){
                System.out.println("Bye");
                break;
            }
            printCountryName(inputCode);
        }

    }

    private String promptUserForCode(){
        System.out.print("Enter country code ('" + QUIT_COMMAND.toUpperCase() + "/" + QUIT_COMMAND.toLowerCase() + "' to quit): ");
        return this.scanner.nextLine().toUpperCase();
    }

    private void printCountryName(String code){

        String countryName = registry.getCountryName(code);

        if(countryName != null){
            System.out.println(countryName);
        }else{
            System.out.println("Not a valid country code.");
        }
    }

}
