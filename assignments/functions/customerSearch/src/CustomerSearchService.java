import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class CustomerSearchService {

    private final List<Customer> customers;

    public CustomerSearchService(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> searchByCountry(String country) {
        return filterAndSort(c -> c.country().contains(country));
    }

    public List<Customer> searchByCompanyName(String companyName) {
        return filterAndSort(c -> c.companyName().contains(companyName));
    }

    public List<Customer> searchByContact(String contact) {
        return filterAndSort(c -> c.contactName().contains(contact));
    }


    private List<Customer> filterAndSort(Predicate<Customer> criteria) {
        return customers.stream()
                .filter(criteria)
                .sorted(Comparator.comparing(Customer::customerId))
                .toList();
    }
}