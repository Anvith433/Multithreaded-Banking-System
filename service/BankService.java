package service;

import model.BankAccount;

public class BankService {

    public void transfer(
            BankAccount fromAccount,
            BankAccount toAccount,
            int amount
    ) {

        BankAccount firstLock;
        BankAccount secondLock;

        // Lock ordering
        if(fromAccount.getAccountId()
                < toAccount.getAccountId()) {

            firstLock =
                    fromAccount;

            secondLock =
                    toAccount;
        }
        else {

            firstLock =
                    toAccount;

            secondLock =
                    fromAccount;
        }

        synchronized(firstLock) {

            System.out.println(
                    Thread.currentThread()
                            .getName()
                    + " locked Account "
                    + firstLock.getAccountId()
            );

            try {

                Thread.sleep(2000);

            } catch(
                    InterruptedException e
            ) {

                e.printStackTrace();
            }

            synchronized(secondLock) {

                System.out.println(
                        Thread.currentThread()
                                .getName()
                        + " locked Account "
                        + secondLock.getAccountId()
                );

                fromAccount.withdraw(
                        amount
                );

                toAccount.deposit(
                        amount
                );

                System.out.println(
                        Thread.currentThread()
                                .getName()
                        + " transferred ₹"
                        + amount
                        + " from Account "
                        + fromAccount.getAccountId()
                        + " to Account "
                        + toAccount.getAccountId()
                );
            }
        }
    }
}