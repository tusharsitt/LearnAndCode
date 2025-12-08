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
        
        boolean running  = true;
        while(running){
            String inputCode = promptUserForCode();
            if(inputCode.equals(QUIT_COMMAND)){
                System.out.println("Bye");
                running = false;
            }else processInputCode(inputCode);
        }

    }

    private String promptUserForCode(){
        System.out.print("Enter country code ('" + QUIT_COMMAND.toUpperCase() + "/" + QUIT_COMMAND.toLowerCase() + "' to quit): ");
        return this.scanner.nextLine().toUpperCase();
    }

    private void processInputCode(String code){

        CountryInfo countryInfo = registry.getCountry(code);

        if(countryInfo != null){
            
            System.out.println( countryInfo.name() + "'s Neighbour: " +  countryInfo.neighbors());

        }else{
            System.out.println("Not a valid country code.");
        }
    }

}
