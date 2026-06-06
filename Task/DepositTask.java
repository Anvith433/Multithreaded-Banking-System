package Task;

import model.BankAccount;

public class DepositTask
implements Runnable {

    private BankAccount account;
    private int amount;

    public DepositTask(
            BankAccount account,
            int amount
    ) {

        this.account =
                account;

        this.amount =
                amount;
    }

    @Override
    public void run() {

        account.deposit(
                amount
        );
    }
}