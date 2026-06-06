# Concurrent Banking System

A **multithreaded banking transaction system built in Core Java** to understand advanced operating system and concurrency concepts such as **thread synchronization, deadlock prevention, thread pooling, producer-consumer architecture, and graceful thread shutdown**.

This project simulates **concurrent money transfers between bank accounts** while ensuring **thread safety and consistency** in a multithreaded environment.

---

## Features

* Multithreaded banking transaction processing
* Concurrent money transfer simulation
* Thread-safe account operations
* Synchronization using `synchronized`
* Deadlock simulation and prevention
* Lock ordering strategy
* Thread pooling using `ExecutorService`
* Producer–Consumer architecture
* Asynchronous transaction processing using `BlockingQueue`
* Graceful worker shutdown using Poison Pill Pattern

---

## Project Architecture

```text
Main (Producer)
        ↓
BlockingQueue<Transaction>
        ↓
TransactionProcessor (Consumer Worker)
        ↓
BankService (Business Logic)
        ↓
BankAccount (Data Model)
```

### Components

#### 1. Main.java

Acts as the entry point of the application.

Responsibilities:

* Creates bank accounts
* Creates thread pool
* Creates worker threads
* Pushes transaction requests into queue
* Manages application lifecycle

---

#### 2. BankAccount.java

Represents a bank account entity.

Responsibilities:

* Stores account information
* Handles deposits
* Handles withdrawals
* Maintains account balance

---

#### 3. Transaction.java

Represents a transfer request.

Responsibilities:

* Stores sender account
* Stores receiver account
* Stores transfer amount
* Supports poison-pill shutdown signal

---

#### 4. BankService.java

Contains core banking business logic.

Responsibilities:

* Executes money transfers
* Applies synchronization
* Prevents race conditions
* Prevents deadlocks using lock ordering

---

#### 5. TransactionProcessor.java

Acts as a worker thread (consumer).

Responsibilities:

* Continuously waits for transactions
* Consumes requests from queue
* Executes transfers using BankService
* Supports graceful shutdown

---

## Concurrency Concepts Implemented

### 1. Synchronization

Used `synchronized` blocks to ensure only one thread accesses shared account resources at a time.

### 2. Race Condition Prevention

Prevented inconsistent balance updates caused by simultaneous thread execution.

### 3. Deadlock Simulation

Intentionally created deadlock scenarios during bidirectional transfers.

Example:

Thread-1:

```text
Account-1 → waiting for Account-2
```

Thread-2:

```text
Account-2 → waiting for Account-1
```

### 4. Deadlock Prevention

Implemented **lock ordering strategy**.

Accounts are always locked in ascending order of account ID:

```text
Smaller Account ID → Larger Account ID
```

This removes **Circular Wait**, preventing deadlocks.

### 5. Thread Pooling

Implemented `ExecutorService` with a fixed thread pool to reuse worker threads efficiently instead of creating new threads repeatedly.

### 6. Producer–Consumer Problem

Implemented using:

* `BlockingQueue`
* Producer (`Main.java`)
* Consumers (`TransactionProcessor.java`)

Transactions are asynchronously processed by worker threads.

### 7. Graceful Shutdown

Implemented **Poison Pill Pattern** to safely terminate worker threads after processing all transactions.

---

## Technologies Used

* Java 21
* Core Java
* Multithreading
* ExecutorService
* BlockingQueue
* OOP
* Synchronization
* Concurrent Programming

---

## Sample Output

```text
pool-1-thread-3 processing transaction...
pool-1-thread-2 processing transaction...
pool-1-thread-1 processing transaction...

pool-1-thread-3 locked Account 1
pool-1-thread-3 locked Account 2
pool-1-thread-3 transferred Rs.3000 from Account 2 to Account 1

pool-1-thread-2 shutting down...
pool-1-thread-1 shutting down...
pool-1-thread-3 shutting down...

Final Account Balances:
Account 1 Balance: Rs.11000
Account 2 Balance: Rs.9000
```

---

## How to Run

### Compile

```bash
javac model/*.java processor/*.java service/*.java task/*.java Main.java
```

### Run

```bash
java Main
```

---

## Key Learnings

Through this project, I learned:

* Thread lifecycle
* Synchronization
* Shared resource handling
* Race conditions
* Deadlocks and Coffman conditions
* Deadlock prevention strategies
* Thread pooling
* Producer–Consumer architecture
* BlockingQueue
* ExecutorService
* Graceful shutdown patterns
* Concurrent system design

---

## Future Improvements

* Add transaction history logging
* Add account validation
* Add exception handling for insufficient balance
* Convert into Spring Boot microservice
* Integrate Kafka for distributed transaction processing




