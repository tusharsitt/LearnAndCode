import java.util.ArrayList;
import java.util.List;

public class BankingService {

    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account acc) { accounts.add(acc); }

    public void processWithdrawals(double amount) {

        for (Account account : accounts) {

            if (account instanceof Withdrawable) {
                ((Withdrawable) account).withdraw(amount);
            } else {
                System.out.println("Skipping withdrawal: Account " + account.accountId + " is restricted.");
            }
        }

    }
}