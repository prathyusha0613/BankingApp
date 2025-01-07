public class BankOperationThread extends Thread {
    private String operation;
    private double amount;
    private Bank bank;

    public BankOperationThread(String operation, double amount, Bank bank) {
        this.operation = operation;
        this.amount = amount;
        this.bank = bank;
    }

    @Override
    public void run() {
        // Perform the deposit or withdrawal operation
        if (operation.equals("deposit")) {
            bank.deposit(amount);
        } else if (operation.equals("withdraw")) {
            bank.withdraw(amount);
        }
    }
}
