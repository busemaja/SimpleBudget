/**
 * This file serves as the connecting class between the app and the BudgetTracker module.
 * 
 * @author Maria Jansson
 */

 package com.simplebudget;

import java.util.ArrayList;

import budgettracker.BudgetTracker;
import budgettracker.TransactionCategories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

class BudgetAdapter {
  BudgetTracker tracker = new BudgetTracker();
  ArrayList<String> transactions = new ArrayList<>();

  // TODO: rename method to show what happens in it
  ArrayList<String> addTransaction(int id, String name, double amount, TransactionCategories category) {
    tracker.addTransactionAndLogIt(name, amount, category);
    String transaction = Integer.toString(id) + " " + name + " " + String.format("%.2f", amount) + " " + category;
    transactions.add(transaction);
    return transactions;
  }
  double getTotalSum() {
    return tracker.getCurrentTotal();
  }

  ObservableList<PieChart.Data> getPercentagesByCategory() {
    String[] percentages = tracker.getPercentagesByCategory();
    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

    for (String entry : percentages) {
        if (entry == null || entry.isBlank()) continue;

        String[] parts = entry.split(" ");
        if (parts.length == 2) {
            String category = parts[0];
            double amount = Double.parseDouble(parts[1].replace("%", "").replace(",", "."));
            pieData.add(new PieChart.Data(category, amount));
        }
    }

    return(pieData);
  }

  boolean saveLogToFile() {
    //tracker.saveLogToFile(filepath);
    return false;
  }
}
