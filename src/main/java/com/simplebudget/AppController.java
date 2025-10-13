/**
 * This file contains the controls for all events in the app, ie what happens when a button is clicked, etc.
 * 
 * @author Maria Jansson
 */

package com.simplebudget;

import java.util.ArrayList;

import budgettracker.TransactionCategories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AppController {
  @FXML
  private Pane popupPane;
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
  private void initialize() {
    transactionCategoryChoiceBox.getItems().addAll(TransactionCategories.values());
  }

  @FXML
  private void listTransactions(ArrayList<String> transactionList) {
    for (String transaction : transactionList) {
      transactionListTextArea.appendText(transaction);
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
