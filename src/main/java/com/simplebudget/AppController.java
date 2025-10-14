/**
 * This file contains the controls for all events in the app, ie what happens when a button is clicked, etc.
 * 
 * @author Maria Jansson
 */

package com.simplebudget;

import java.util.ArrayList;

import budgettracker.TransactionCategories;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class AppController {
  @FXML
  private AnchorPane rootPane;
  @FXML
  private Pane popupPane;
  @FXML
  private Pane messagePopupPane;
  @FXML
  private TextArea messageTextArea;
  @FXML
  private TextArea transactionListTextArea;
  private static int idNumberCounter;
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
  private void initialize() {
    String currentIdNumber = Integer.toString(idNumberCounter);
    idField.setText(currentIdNumber);
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

      //BudgetAdapter.addTransaction(name, amount, category);
      
      nameField.setText("");
      amountField.setText("");
      transactionCategoryChoiceBox.getSelectionModel().clearSelection();
      transactionCategoryChoiceBox.setValue(null);
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
    totalSumTextField.setText("Total sum: " + BudgetAdapter.getTotalSum());
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
    if (BudgetAdapter.saveLogToFile()) {
      openMessagePopup("Log saved to file.");
    } else {
      openMessagePopup("Could not save to file.");
    }
    
  }

  @FXML
  private void openPopup() {
    popupPane.setVisible(true);
  }

  @FXML
  private void closePopup() {
    popupPane.setVisible(false);
  }

  @FXML
  private void closeApp(ActionEvent event) {
    System.exit(0);
  }

}
