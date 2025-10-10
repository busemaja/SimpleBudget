package budgettracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a single log post for a transaction.
 * 
 * @author Maria Jansson
 */

class LogEntry {
  private final LocalDateTime timestamp;
  private final Action action;
  private final int transactionId;
  private final TransactionCategories category;
  private final String name;
  private final double amount;

  // Add actions to your heart's content, but don't forget to add and implement methods in BudgetTracker.
  enum Action {
    ADD, 
    REMOVE
  }

  LogEntry(Action action, TransactionCategories category, int transactionId, String name, double amount) {
    this.timestamp = LocalDateTime.now();
    this.action = action;
    this.category = category;
    this.transactionId = transactionId;
    this.name = name;
    this.amount = amount;
  }

    // Example of return string (values separeted by tab): ADD	25	2025-09-25 15:26	FOOD	Fika	125,00kr
  String toFormattedString() {
    DateTimeFormatter wantedDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedTimestamp = this.timestamp.format(wantedDateTimeFormat);
    return this.action + "\t" 
      + this.transactionId + "\t" 
      + formattedTimestamp + "\t" 
      + this.category + "\t" 
      + this.name + "\t" 
      + String.format("%.2f", this.amount) + "kr";
  }
  
}
