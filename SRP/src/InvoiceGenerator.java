public record InvoiceGenerator(double taxFactor) {

    public void printInvoice(Product product) {
        System.out.println("\n--- INVOICE ---");
        System.out.println("Item: " + product.name());
        System.out.println("Price: $" + product.price());
        System.out.println("Tax: $" + calculateTax(product));
        System.out.println("Total: $" + calculateTotal(product));
        System.out.println("Total: $" + calculateTotal(product));
        System.out.println("----------------\n");
    }

    private double calculateTax(Product product) {
        return product.price() * this.taxFactor;
    }

    private double calculateTotal(Product product) {
        return product.price() + calculateTax(product);
    }

}