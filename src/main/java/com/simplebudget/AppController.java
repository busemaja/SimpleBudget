/**
 * This file contains the controls for all events in the app, ie what happens when a button is clicked, etc.
 * 
 * @author Maria Jansson
 */

package com.simplebudget;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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
  private BudgetTrackerAdapter adapter;
  private static int idNumberCounter;
  @FXML private AnchorPane rootPane;
  @FXML private Pane chartPopupPane;
  @FXML private Pane messagePopupPane;
  @FXML private TextArea messageTextArea;
  @FXML private ListView<String> transactionListView;
  @FXML private TextField idField;
  @FXML private TextField nameField;
  @FXML private TextField amountField;
  @FXML private ChoiceBox<String> transactionCategoryChoiceBox;
  @FXML private TextArea largestTransactionTextArea;
  @FXML private TextField totalSumTextField;
  @FXML private PieChart categoryPercentageChart;
  @FXML private Button addButton;
  @FXML private Button removeButton;

  void setStage(Stage stage) {
    this.stage = stage;
  }

  void setAdapter(BudgetTrackerAdapter adapter) {
    this.adapter = adapter;
  }

  @FXML private void initialize() {
    updateIdFieldWithNextId();
    transactionCategoryChoiceBox.getItems().addAll(adapter.getCategories());
    setUpTransactionListViewListener();
    Platform.runLater(() -> rootPane.requestFocus());
  }

  /*
   * When clicked, a transaction is transferred to the textFields and can be removed completely from the app.
   */
  private void setUpTransactionListViewListener() {
    transactionListView.setOnMouseClicked(event -> {
      String selectedItem = transactionListView.getSelectionModel().getSelectedItem();
      if (selectedItem != null) {
        String[] parts = selectedItem.split(" ");

        if (parts.length == 4) {
          idField.setText(parts[0]);
          nameField.setText(parts[1]);
          amountField.setText(parts[2]);
          transactionCategoryChoiceBox.setValue(parts[3]);
          removeButton.setDisable(false);
          addButton.setDisable(true);
        }
      }
    });
  }

  private void updateIdFieldWithNextId() {
    idField.setText(Integer.toString(idNumberCounter));
    idNumberCounter++;
  }

  @FXML private void onAddTransaction() {
    if (isInputPresent()) {
      String name = nameField.getText().replace(" ", "_");
      double amount = Double.parseDouble(amountField.getText().replace(',', '.'));
      String category = transactionCategoryChoiceBox.getSelectionModel().getSelectedItem();

      if (adapter.addTransaction(idNumberCounter, name, amount, category)) {
        clearInputFields();
        updateTransactionListAndTotalSum();
        updateIdFieldWithNextId();
        updateLargestTransactionView();
      }
    } else {
      openMessagePopup("All fields must be filled in.");
    }
  }

  /*
   * No fields are allowed to be left empty on input.
   */
  private boolean isInputPresent() {
    return !nameField.getText().isBlank()
      && !amountField.getText().isBlank()
      && transactionCategoryChoiceBox.getSelectionModel().getSelectedItem() != null;
  }

  private void clearInputFields() {
    nameField.setText("");
    amountField.setText("");
    transactionCategoryChoiceBox.getSelectionModel().clearSelection();
    transactionCategoryChoiceBox.setValue(null);
  }

  private void updateLargestTransactionView() {
    largestTransactionTextArea.setText("Info on the largest transaction: \n" + adapter.getInfoOnLargestTransaction());
  }

  @FXML private void onRemoveTransaction() {
    int id = Integer.parseInt(idField.getText());
    if (adapter.removeTransaction(id)) {
      updateIdFieldWithNextId();
      clearInputFields();
      updateTransactionListAndTotalSum();
      updateLargestTransactionView();
      removeButton.setDisable(true);
      addButton.setDisable(false);
      openMessagePopup("Transaction removed.");
    } else {
      openMessagePopup("Could not remove transaction.");
    }
  }

  private void updateTransactionListAndTotalSum() {
    transactionListView.getItems().clear();
    ArrayList<String> transactions = adapter.getTransactionList();
    for (String transaction : transactions) {
      transactionListView.getItems().add(transaction);
    }
    totalSumTextField.setText("Total sum: " + String.format("%.2f", adapter.getTotalSum()));
  }

  @FXML private void openMessagePopup(String message) {
    messageTextArea.setText(message);
    messagePopupPane.setVisible(true);
  }

  @FXML private void closeMessagePopup() {
    messagePopupPane.setVisible(false);
  }

  @FXML private void onSaveLogToFile() {
    if (adapter.getTotalSum() != 0) {
      DirectoryChooser directoryChooser = new DirectoryChooser();
      directoryChooser.setTitle("Choose folder for logs");

      File selectedDirectory = directoryChooser.showDialog(stage);
      if (selectedDirectory != null) {
        String path = selectedDirectory.getAbsolutePath();
        boolean success = adapter.saveLogToFile(path);
        openMessagePopup(success ? "Log saved to file." : "Could not save to file.");
      } else {
        openMessagePopup("No folder selected.");
      }
    } else {
      openMessagePopup("No transactions in the log.");
    }
  }

  /*
   * Currently shows the values strangely, e.g. labels are shown in graph even if value is 0.
   */
  @FXML private void onShowGraph() {
    ObservableList<PieChart.Data> chartData = adapter.getCategoryDataForPieChart();
    categoryPercentageChart.setData(chartData);
    chartPopupPane.setVisible(true);
  }

  @FXML private void onCloseGraph() {
    chartPopupPane.setVisible(false);
  }

  @FXML private void onExitApp() {
    Platform.exit();
  }

}
