package model;

public class BankAccount {

    private int accountId;
    private int balance;

    public BankAccount(
            int accountId,
            int initialBalance
    ) {

        this.accountId =
                accountId;

        this.balance =
                initialBalance;
    }

    public int getAccountId() {

        return accountId;
    }

    public int getBalance() {

        return balance;
    }

    public void deposit(
            int amount
    ) {

        balance += amount;
    }

    public void withdraw(
            int amount
    ) {

        balance -= amount;
    }

    public void printAccountDetails() {

        System.out.println(
                "Account "
                + accountId
                + " Balance: ₹"
                + balance
        );
    }
}