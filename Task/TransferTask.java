package Task;

import model.BankAccount;
import service.BankService;

public class TransferTask
implements Runnable {

    private BankAccount fromAccount;
    private BankAccount toAccount;
    private int amount;
    private BankService bankService;

    public TransferTask(
            BankAccount fromAccount,
            BankAccount toAccount,
            int amount,
            BankService bankService
    ) {

        this.fromAccount =
                fromAccount;

        this.toAccount =
                toAccount;

        this.amount =
                amount;

        this.bankService =
                bankService;
    }

    @Override
    public void run() {

        bankService.transfer(
                fromAccount,
                toAccount,
                amount
        );
    }
}