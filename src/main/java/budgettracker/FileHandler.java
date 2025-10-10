package budgettracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The file handler class for the module, can currently only write the transaction log to file.
 * 
 * @author Maria Jansson
 */

class FileHandler {
  private String filePath;

/**
 * Must be set before calling saveLogToFile()
 * @param filePath - Include the file name and format! Example file path: "src/main/resources/transactionlog.txt"
 */
  void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Please remember to set the file path (using setFilePath()) before trying to save to file, otherwise it will throw an exception.
   * @param transactionLog - ArrayList of strings
   */
  void saveLogToFile(ArrayList<String> transactionLog) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write("Action\tID\tTime stamp\tCategory\tName\tAmount");
      writer.newLine();
      for (String logEntry : transactionLog) {
        writer.write(logEntry);
        writer.newLine();
      }
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }
}
