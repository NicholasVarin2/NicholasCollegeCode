
import java.util.Date;

/*
 * This class represents a transaction that has a transaction date,type, amount,
 * balance, and description.
 * @author Nicholas Varin
 * @param transactionDate Date: The date of the transaction
 * @param transactionType char: The type of transaction (withdrawal or deposit)
 * @param amount double: The amount of the transaction
 * @param balance double: The balance after the transaction
 * @param description String: The description of the transaction
     * @version 1.0
     * @since 10 Feb 2025
 */
public class Transaction {

    private final Date transactionDate;
    private final char transactionType;
    private final double amount;
    private final double balance;
    private final String description;

    /**
     * Constructor for the Transaction class that has a transaction type,
     * amount,balance, and description
     *
     * @param transactionType char: The type of transaction (withdrawal or
     * deposit)
     * @param amount double: The amount of the transaction
     * @param balance double: The balance after the transaction
     * @param description String: The description of the transaction
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public Transaction(char transactionType, double amount, double balance, String description) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.transactionDate = new Date();
    }

    public char getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Type: " + transactionType + "\tAmount: $" + String.format("%.2f", amount) + "\tBalance: $" + String.format("%.2f", balance) + "\tDescription: " + description;
    }

}
