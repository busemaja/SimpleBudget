package budgettracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a single transaction.
 * Consists of Id, name (descriptive, ie "Coffe" or "Petrol"), amount, timestamp, category.
 * 
 * @author Maria Jansson
 */

class Transaction {
  private static int idCounter = 1;
  private final int Id;
  private final String name;
  private final double amount;
  private final LocalDateTime timestamp;
  private final TransactionCategories category;
  
  Transaction(String name, double amount, TransactionCategories category) {
    this.Id = idCounter++;
    this.name = name;
    this.amount = amount;
    this.timestamp = LocalDateTime.now();
    this.category = category;
  }
  
  String getTimestamp() {
    DateTimeFormatter wantedDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedTimestamp = this.timestamp.format(wantedDateTimeFormat);
    return formattedTimestamp;
  }

  String getName() {
    return this.name;
  }

  double getAmount() {
    return this.amount;
  }
  
  TransactionCategories getCategory() {
    return this.category;
  }

  int getId() {
    return this.Id;
  }

  // Example of return string (values separeted by tab): 25	2025-09-25 15:26	FOOD	Fika	125,00kr
  String toFormattedString() {
    return this.getId() + "\t" 
      + this.getTimestamp() + "\t" 
      + this.getCategory() + "\t" 
      + this.getName() + "\t" 
      + String.format("%.2f", this.getAmount()) + "kr";
  }
}
