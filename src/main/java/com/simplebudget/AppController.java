/**
 * This file contains the controls for all events in the app, ie what happens when a button is clicked, etc.
 * 
 * @author Maria Jansson
 */

package com.simplebudget;

import java.util.ArrayList;

import budgettracker.TransactionCategories;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class AppController {
  private final BudgetAdapter adapter = new BudgetAdapter();
  private static int idNumberCounter;
  private ArrayList<String> transactions = new ArrayList<>();
  @FXML
  private AnchorPane rootPane;
  @FXML
  private Pane chartPopupPane;
  @FXML
  private Pane messagePopupPane;
  @FXML
  private TextArea messageTextArea;
  @FXML
  private TextArea transactionListTextArea;
  @FXML
  private TextField idField;
  @FXML
  private TextField nameField;
  @FXML
  private TextField amountField;
  @FXML
  private ChoiceBox<TransactionCategories> transactionCategoryChoiceBox;
  @FXML
  private TextField totalSumTextField;
  @FXML
  private PieChart categoryPercentageChart;

  @FXML
  private void initialize() {
    idField.setText(Integer.toString(idNumberCounter));
    idNumberCounter++;
    transactionCategoryChoiceBox.getItems().addAll(TransactionCategories.values());
    Platform.runLater(() -> rootPane.requestFocus());
  }

  @FXML
  private void onAddTransaction() {
    if (isInputvalid()) {
      String name = nameField.getText();
      double amount = Double.parseDouble(amountField.getText());
      TransactionCategories category = transactionCategoryChoiceBox.getSelectionModel().getSelectedItem();

      transactions = adapter.addTransaction(idNumberCounter, name, amount, category);
      
      nameField.setText("");
      amountField.setText("");
      transactionCategoryChoiceBox.getSelectionModel().clearSelection();
      transactionCategoryChoiceBox.setValue(null);
      updateTransactionListAndTotalSum();
      idField.setText(Integer.toString(idNumberCounter));
      idNumberCounter++;
    } else {
      openMessagePopup("All fields must be filled in.");
    }
  }

  private boolean isInputvalid() {
    return !nameField.getText().isBlank()
        && !amountField.getText().isBlank()
        && transactionCategoryChoiceBox.getSelectionModel().getSelectedItem() != null;
  }

  private void updateTransactionListAndTotalSum() {
    transactionListTextArea.setText("");
    for (String transaction : transactions) {
      transactionListTextArea.appendText(transaction + "\n");
    }
    totalSumTextField.setText("Total sum: " + String.format("%.2f", adapter.getTotalSum()));
  }

  @FXML
  private void openMessagePopup(String message) {
    messageTextArea.setText(message);
    messagePopupPane.setVisible(true);
  }

  @FXML
  private void closeMessagePopup() {
    messagePopupPane.setVisible(false);
  }

  @FXML
  private void listTransactions(ArrayList<String> transactionList) {
    for (String transaction : transactionList) {
      transactionListTextArea.appendText(transaction);
    }
  }

  @FXML
  private void onSaveLogToFile() {
    if (adapter.saveLogToFile()) {
      openMessagePopup("Log saved to file.");
    } else {
      openMessagePopup("Could not save to file.");
    }
    
  }

  @FXML
  private void openChartPopup() {
    ObservableList<PieChart.Data> chartData = adapter.getPercentagesByCategory();
    categoryPercentageChart.setData(chartData);
    chartPopupPane.setVisible(true);
  }

  @FXML
  private void closeChartPopup() {
    chartPopupPane.setVisible(false);
  }

  @FXML
  private void closeApp(ActionEvent event) {
    System.exit(0);
  }

}
