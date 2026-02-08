public class Wallet {
    private double balance;

    public Wallet(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean hasSufficientFunds(double amount) {
        return balance >= amount;
    }

    public void deductFunds(double amount) {
        if (hasSufficientFunds(amount)) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}