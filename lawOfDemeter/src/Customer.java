public class Customer {
    private final String givenName;
    private final String surname;
    private final Wallet wallet;

    public Customer(String givenName, String surname, Wallet wallet) {
        this.givenName = givenName;
        this.surname = surname;
        this.wallet = wallet;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurname() {
        return surname;
    }

    public boolean pay(double amount) {
        if (wallet.hasSufficientFunds(amount)) {
            wallet.deductFunds(amount);
            return true;
        }
        return false;
    }
}