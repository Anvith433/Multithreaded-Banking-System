package model;

public class Transaction {

    private BankAccount fromAccount;
    private BankAccount toAccount;
    private int amount;
    private boolean stopSignal;

    // Normal transaction constructor
    public Transaction(
            BankAccount fromAccount,
            BankAccount toAccount,
            int amount
    ) {

        this.fromAccount =
                fromAccount;

        this.toAccount =
                toAccount;

        this.amount =
                amount;

        this.stopSignal =
                false;
    }

    // Stop signal constructor
    public Transaction(
            boolean stopSignal
    ) {

        this.stopSignal =
                stopSignal;
    }

    public boolean isStopSignal() {

        return stopSignal;
    }

    public BankAccount getFromAccount() {

        return fromAccount;
    }

    public BankAccount getToAccount() {

        return toAccount;
    }

    public int getAmount() {

        return amount;
    }
}