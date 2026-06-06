package processor;

import model.Transaction;
import service.BankService;

import java.util.concurrent.BlockingQueue;

public class TransactionProcessor
implements Runnable {

    private BlockingQueue<Transaction>
            transactionQueue;

    private BankService bankService;

    public TransactionProcessor(
            BlockingQueue<Transaction>
                    transactionQueue,
            BankService bankService
    ) {

        this.transactionQueue =
                transactionQueue;

        this.bankService =
                bankService;
    }

    @Override
    public void run() {

        try {

            while(true) {

                Transaction transaction =
                        transactionQueue.take();

                // Stop worker
                if(transaction.isStopSignal()) {

                    System.out.println(
                            Thread.currentThread()
                                    .getName()
                            + " shutting down..."
                    );

                    break;
                }

                System.out.println(
                        Thread.currentThread()
                                .getName()
                        + " processing transaction..."
                );

                bankService.transfer(
                        transaction
                                .getFromAccount(),

                        transaction
                                .getToAccount(),

                        transaction
                                .getAmount()
                );
            }

        } catch(
                InterruptedException e
        ) {

            System.out.println(
                    Thread.currentThread()
                            .getName()
                    + " interrupted."
            );

            Thread.currentThread()
                    .interrupt();
        }
    }
}