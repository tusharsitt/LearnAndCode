public class Paperboy {
    public void collectPayment(Customer customer, double amount) {
        String fullName = customer.getGivenName() + " " + customer.getSurname();

        if (customer.pay(amount)) {
            System.out.printf("Transaction Successful: Collected %.2f from %s.%n",
                    amount, fullName);
        } else {
            System.out.printf("Transaction Declined: %s has insufficient funds for the amount of %.2f.%n",
                    fullName, amount);
        }
    }
}