/**
 * This file contains the controls for all events in the app, ie what happens when a button is clicked, etc.
 * 
 * @author Maria Jansson
 */

package com.simplebudget;

import java.io.File;
import java.util.ArrayList;

import budgettracker.TransactionCategories;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class AppController {
  private Stage stage;
  private BudgetAdapter adapter;
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
  private ListView<String> transactionListView;
  @FXML
  private TextField idField;
  @FXML
  private TextField nameField;
  @FXML
  private TextField amountField;
  @FXML
  private ChoiceBox<TransactionCategories> transactionCategoryChoiceBox; // går detta att lösa så att controllern får datan från adaptern istället för att hämta själv?
  @FXML
  private TextArea largestTransactionTextField;
  @FXML
  private TextField totalSumTextField;
  @FXML
  private PieChart categoryPercentageChart;
  @FXML
  private Button removeButton;

  void setStage(Stage stage) {
      this.stage = stage;
  }

  void setAdapter(BudgetAdapter adapter) {
    this.adapter = adapter;
  }

  @FXML
  private void initialize() {
    idField.setText(Integer.toString(idNumberCounter));
    idNumberCounter++;
    transactionCategoryChoiceBox.getItems().addAll(TransactionCategories.values());
    
    transactionListView.setOnMouseClicked(event -> {
      String selectedItem = transactionListView.getSelectionModel().getSelectedItem();
      if (selectedItem != null) {
          String[] parts = selectedItem.split(" ");

        if (parts.length >= 4) {
            idField.setText(parts[0]);
            nameField.setText(parts[1]);
            amountField.setText(parts[2]);
            TransactionCategories category = TransactionCategories.valueOf(parts[3]);
            transactionCategoryChoiceBox.setValue(category);
        }
      }
      removeButton.setDisable(false);
    });

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
      idNumberCounter++;
      idField.setText(Integer.toString(idNumberCounter));

      largestTransactionTextField.setText("Info the largest transaction: \n" + adapter.getInfoOnLargestTransaction());
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
    transactionListView.getItems().clear();
    for (String transaction : transactions) {
      transactionListView.getItems().add(transaction);
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
      transactionListView.getItems().add(transaction);
    }
  }

  @FXML
  private void onSaveLogToFile() {
      DirectoryChooser directoryChooser = new DirectoryChooser();
      directoryChooser.setTitle("Choose folder for logs");

      File selectedDirectory = directoryChooser.showDialog(stage);

      if (selectedDirectory != null) {
          String path = selectedDirectory.getAbsolutePath();
          System.out.println("Chosen folder: " + path);

          if (adapter.saveLogToFile(path)) {
              openMessagePopup("Log saved to file.");
          } else {
              openMessagePopup("Could not save to file.");
          }
      } else {
          openMessagePopup("No folder selected.");
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
