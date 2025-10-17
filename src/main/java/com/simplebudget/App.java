/**
 * This file is the starting point of the SimpleBudget app.
 *
 * @author Maria Jansson
 */
package com.simplebudget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
    
    // Create controller manually and set adapter BEFORE loading FXML
    AppController controller = new AppController();
    controller.setStage(stage);
    controller.setAdapter(new BudgetTrackerAdapter());
    
    // Set the controller so FXML uses our pre-configured instance
    loader.setController(controller);
    
    Scene scene = new Scene(loader.load());

    stage.setScene(scene);
    stage.setTitle("Simple Budget App");
    stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

