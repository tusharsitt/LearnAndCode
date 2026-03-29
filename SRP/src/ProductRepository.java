import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private final Map<Integer, Product> database = new HashMap<>();

    public void addProduct(Product product) {
        database.put(product.id(), product);
        System.out.println("System: Product '" + product.name() + "' saved.");
    }

    public Product getProduct(int id) {
        return database.get(id);
    }
}