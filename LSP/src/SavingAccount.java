public class SavingAccount extends Account implements Withdrawable{

    SavingAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from Savings.");
        }else {
            System.out.println("Insufficient funds.");
        }
    }
}

