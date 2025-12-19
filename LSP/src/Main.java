public class Main {
    public static void main(String[] args) {
        BankingService service = new BankingService();

        service.addAccount(new SavingAccount("SA-101", 5000));
        service.addAccount(new FixedDepositAccount("FD-999", 10000));

        service.processWithdrawals(1000);
    }
}