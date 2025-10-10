package budgettracker;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A lightweight Java module for secure transaction management and financial analysis.
 * Think of it as a digital form of the classic account book, where you list all of your transactions, but with a few more functions making it easier and more fun to keep track and analyze!
 * 
 * @author Maria Jansson
 * @version 1.0.0
 */
public class BudgetTracker {
  private final ArrayList <Transaction> transactions = new ArrayList<>();
  private final ArrayList <LogEntry> transactionLog = new ArrayList<>();
  private final FileHandler fileHandler = new FileHandler();

  public BudgetTracker() {}

  /**
   * Adds a transaction to the list of transactions, and also adds it to the log.
   * @param transactionName - a descriptive name, for example "Coffee" or "Petrol".
   * @param amount - primitive type double
   * @param category - enums found in the file TransactionCategories, change there if needed.
   * @return - the id of the transaction created
   * @throws IllegalArgumentException - if transactionName is null/empty, amount is NaN/infinite, or category is null
   */
  public int addTransactionAndLogIt(String transactionName, double amount, TransactionCategories category) {
    if (transactionName == null || transactionName.isEmpty()) {
        throw new IllegalArgumentException("Transaction name cannot be null or empty.");
    }
    if (Double.isNaN(amount) || Double.isInfinite(amount)) {
        throw new IllegalArgumentException("Amount must be a valid number.");
    }
    if (category == null) {
        throw new IllegalArgumentException("Category cannot be null.");
    }

    Transaction transaction = new Transaction(transactionName, amount, category);
    transactions.add(transaction);
    logTransaction(transaction, LogEntry.Action.ADD);
    return transaction.getId();
  }

  /**
   * Removes a transaction from the list of transactions, and also adds the removal to the log.
   * @param transactionId - the current transaction's unique ID
   * @throws IllegalArgumentException - if transactionId is less than 1
   */
  public void removeTransactionAndLogIt(int transactionId) {
    if (transactionId < 1) {
      throw new IllegalArgumentException("ID must be a number above 0.");
    }
    try {
      Transaction transaction = getTransaction(transactionId);
      Transaction transactionCopyForLog = new Transaction(
        transaction.getName(),
        transaction.getAmount(),
        transaction.getCategory()
      );
      if (transactions.remove(transaction)) {
        logTransaction(transactionCopyForLog, LogEntry.Action.REMOVE);
      }
    } catch (NoSuchElementException e) {
      System.out.println("There is no transaction with that ID.");
    }
  }

  /**
   * Returns the total sum of all transactions in the list of transactions.
   * @return - the sum as a double
   */
  public double getCurrentTotal() {
    double sum = 0;
    for (Transaction transaction : transactions) {
      sum += transaction.getAmount();
    }
    return sum;
  }

  /**
   * Returns the total sum of all transactions in the list of transactions that belong to a certain category.
   * @param category - found in the file TransactionCategories
   * @return - the sum as a double
   */
  public double getCurrentTotalByCategory(TransactionCategories category) {
    double sum = 0;
    for (Transaction transaction : transactions) {
      if(transaction.getCategory() == category) {
        sum += transaction.getAmount();
      }
    }
    return sum;
  }

  /**
   * Returns the info of the transaction with the largest amount in the list of transactions.
   * @return - the info as a String, formatted as: "yyyy-MM-dd HH:mm, CATEGORY, Name of transaction, Amount(with 2 decimals)kr" with tabs between the fields instead of commas.
   */
  public String getInfoOnLargestTransaction() {
    ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
    transactionsCopy.sort((transaction1, transaction2) -> 
      Double.compare(transaction1.getAmount(), transaction2.getAmount()));
    
    Transaction transaction = transactionsCopy.get(transactionsCopy.size() - 1);
    String transactionInfo = transaction.toFormattedString();
    
    return transactionInfo;
  }

  /**
   * @return - a String array of how large a percentage value each category is.
   */
  public String[] getPercentagesByCategory() {
    String[] percentages = new String[TransactionCategories.values().length];
    double sumOfAllCategories = getCurrentTotal();
    if (sumOfAllCategories == 0) {
      return percentages;
    }
    TransactionCategories[] categories = TransactionCategories.values();

    // Loop the categories, calculate their respective percentage, create String with category name and percentage, add to String array
    for (int i = 0; i < categories.length; i++) {
      TransactionCategories category = categories[i];
      double categorySum = getCurrentTotalByCategory(category);
      double categoryPercentage = (categorySum / sumOfAllCategories) * 100;
      String categoryPercentageString = String.format("%.2f", categoryPercentage) + "%";
      String result = category + " " + categoryPercentageString;
      percentages[i] = result;
    }

    return percentages;
  }

  /**
   * @return - an arraylist of all logged transaction events as strings.
   */
  public ArrayList<String> getTransactionLog() {
    ArrayList <String> transactionLogCopy = new ArrayList<>();
    for (LogEntry entry : transactionLog) {
      transactionLogCopy.add(entry.toFormattedString());
    }
    return transactionLogCopy;
  }

  /**
   * Saves all the logged transaction actions, with complete transaction info, as a text file.
   * @param filepath - Include the file name and format! Example file path: "src/main/resources/transactionlog.txt"
   * @throws IllegalArgumentException - if filepath is null or empty
   */
  public void saveLogToFile(String filepath) {
    if (filepath == null || filepath.isBlank()) {
        throw new IllegalArgumentException("File path must not be null or empty.");
    }
    fileHandler.setFilePath(filepath);
    fileHandler.saveLogToFile(getTransactionLog());
  }
  
  // Returns the actual transaction object, so be careful what you do with it!
  // Throws NoSuchElementException if there is no ID match
  private Transaction getTransaction(int transactionId) {
    for (Transaction transaction : transactions) {
      if (transaction.getId() == transactionId) {
        return transaction;
      }
    }
    throw new NoSuchElementException("Transaction with ID " + transactionId + " not found");
  }

  // Logs the transaction, available actions found in class LogEntry.
  // Throws RuntimeException if action is not found.
  private void logTransaction(Transaction transaction, LogEntry.Action action) {
    LogEntry log;
    switch (action) {
      case ADD:
        log = new LogEntry(LogEntry.Action.ADD, transaction.getCategory(), transaction.getId(), transaction.getName(), transaction.getAmount());
      break;
      case REMOVE:
        log = new LogEntry(LogEntry.Action.REMOVE, transaction.getCategory(), transaction.getId(), transaction.getName(), transaction.getAmount());
      break;
      default:
        throw new RuntimeException("Action not found.");
    }
    transactionLog.add(log);
  }

}
