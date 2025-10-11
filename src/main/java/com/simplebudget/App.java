/**
 * This file is the starting point of the SimpleBudget app.
 *
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
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Simple Budget App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

