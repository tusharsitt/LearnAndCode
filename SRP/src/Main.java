public class Main {
    public static void main(String[] args) {

        ProductRepository repo = new ProductRepository();
        InvoiceGenerator invoiceGen = new InvoiceGenerator(0.10);

        Product laptop = new Product(101, "MacBook Pro", 1999.99);

        repo.addProduct(laptop);

        Product retrievedProduct = repo.getProduct(101);

        if (retrievedProduct != null) {
            invoiceGen.printInvoice(retrievedProduct);
        }

    }
}