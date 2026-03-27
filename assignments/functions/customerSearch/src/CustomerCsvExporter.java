import java.util.List;
import java.util.stream.Collectors;


public class CustomerCsvExporter {

    public String exportToCsv(List<Customer> data) {
        if (data == null || data.isEmpty()) {
            return "";
        }

        return data.stream()
                .map(this::formatRow)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatRow(Customer customer) {
        return String.format("%s,%s,%s,%s",
                customer.customerId(),
                customer.companyName(),
                customer.contactName(),
                customer.country()
        );
    }
}