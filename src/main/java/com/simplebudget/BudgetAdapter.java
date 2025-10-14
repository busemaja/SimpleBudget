/**
 * This file serves as the connecting class between the app and the BudgetTracker module.
 * 
 * @author Maria Jansson
 */

 package com.simplebudget;

import java.util.ArrayList;

import budgettracker.BudgetTracker;
import budgettracker.TransactionCategories;

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
  boolean saveLogToFile() {
    //tracker.saveLogToFile(filepath);
    return false;
  }
}
