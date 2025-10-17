/**
 * This file serves as the connecting class between the app and the BudgetTracker module.
 * It handles all the calculations and communication between the module and the UI-controller class.
 * 
 * @author Maria Jansson
 */

 package com.simplebudget;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import budgettracker.BudgetTracker;
import budgettracker.TransactionCategories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

class BudgetTrackerAdapter {
  BudgetTracker tracker = new BudgetTracker();
  ArrayList<String> transactions = new ArrayList<>();

  boolean addTransaction(int id, String name, double amount, String category) {
    try {
      TransactionCategories transactionCategory = TransactionCategories.valueOf(category);
      tracker.addTransactionAndLogIt(name, amount, transactionCategory);
      String transaction = Integer.toString(id) + " " + name + " " + String.format("%.2f", amount) + " " + category;
      transactions.add(transaction);

      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  boolean removeTransaction(int id) {
    try {
      tracker.removeTransactionAndLogIt(id);

      String idAsString = Integer.toString(id);
      for (String transaction : transactions) {
        if (transaction.startsWith(idAsString + " ")) {
          transactions.remove(transaction);
          break;
        }
      }
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  double getTotalSum() {
    return tracker.getCurrentTotal();
  }

  String getInfoOnLargestTransaction() {
    return tracker.getInfoOnLargestTransaction();
  }

  /**
   * @return the category percentages of all listed transactions.
   */
  ObservableList<PieChart.Data> getCategoryDataForPieChart () {
    String[] percentages = tracker.getPercentagesByCategory();
    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

    for (String entry : percentages) {
      if (entry == null || entry.isBlank()) continue;

      String[] parts = entry.split(" ");
      if (parts.length == 2) {
        String category = parts[0];
        double amount = Double.parseDouble(parts[1].replace("%", "").replace(",", "."));
        if (amount == 0.0)
        {
          continue;
        }
        pieData.add(new PieChart.Data(category, amount));
      }
    }
    return(pieData);
  }

  /**
   * The log is saved under name: transactionlog_"currentDate".txt
   */
  boolean saveLogToFile(String directoryPath) {
    LocalDate date = LocalDate.now();
    Path filePath = Path.of(directoryPath, "transactionlog_" + date + ".txt");
    try {
      tracker.saveLogToFile(filePath.toString());
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  ObservableList<String> getCategories() {
    ObservableList<String> list = FXCollections.observableArrayList();
    for (TransactionCategories category : TransactionCategories.values()) {
      list.add(category.name());
    }
    return list;
  }

  ArrayList<String> getTransactionList() {
    return new ArrayList<>(transactions);
  }
}
