import model.BankAccount;
import model.Transaction;
import processor.TransactionProcessor;
import service.BankService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(
            String[] args
    ) {

        BankAccount account1 =
                new BankAccount(
                        1,
                        10000
                );

        BankAccount account2 =
                new BankAccount(
                        2,
                        10000
                );

        BankService bankService =
                new BankService();

        BlockingQueue<Transaction>
                transactionQueue =
                new LinkedBlockingQueue<>();

        // Thread Pool with 3 workers
        ExecutorService executor =
                Executors.newFixedThreadPool(
                        3
                );

        // Start Consumers
        executor.submit(
                new TransactionProcessor(
                        transactionQueue,
                        bankService
                )
        );

        executor.submit(
                new TransactionProcessor(
                        transactionQueue,
                        bankService
                )
        );

        executor.submit(
                new TransactionProcessor(
                        transactionQueue,
                        bankService
                )
        );

        // Producer adds transactions
        transactionQueue.add(
                new Transaction(
                        account1,
                        account2,
                        2000
                )
        );

        transactionQueue.add(
                new Transaction(
                        account2,
                        account1,
                        3000
                )
        );

        transactionQueue.add(
                new Transaction(
                        account1,
                        account2,
                        1000
                )
        );

        transactionQueue.add(
                new Transaction(
                        account2,
                        account1,
                        1500
                )
        );

        transactionQueue.add(
                new Transaction(
                        account1,
                        account2,
                        500
                )
        );

        // Poison Pills (1 for each worker)
        transactionQueue.add(
                new Transaction(true)
        );

        transactionQueue.add(
                new Transaction(true)
        );

        transactionQueue.add(
                new Transaction(true)
        );

        // Graceful shutdown
        executor.shutdown();

        try {

            executor.awaitTermination(
                    15,
                    TimeUnit.SECONDS
            );

        } catch(
                InterruptedException e
        ) {

            e.printStackTrace();
        }

        System.out.println(
                "\nFinal Account Balances:"
        );

        account1.printAccountDetails();
        account2.printAccountDetails();
    }
}