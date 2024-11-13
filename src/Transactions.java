import java.util.Date;
import java.util.UUID;

public class Transactions {
private String transactionId;
private Date date;
private String type;//(withdraw/deposit)
 private double amount;
    public Transactions(String type, double amount) {
        this.transactionId = UUID.randomUUID().toString();  // Unique transaction ID
        this.date = new Date();  // Current date and time
        this.type = type;
        this.amount = amount;
    }
    public String getTransactionId() {
        return transactionId;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Date: " + date + ", Type: " + type + ", Amount: " + amount;
    }


}
