package com.example.c482_assessment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ApplicationLoader class
 * subclass of Application
 * creates the Inventory instance for storing data
 * launches JavaFX windows with Main class
 *
 * @author Sheldon Handler
 */
public class ApplicationLoader extends Application {

    /**
     * Inventory instance to save entered data into
     */
    //FIXME: 7/31/2022 create code to load all inventory data from either a file or database and write changes everytime an addition, modification, or removal is made
    public static Inventory inventory = new Inventory();

    /**
     * default constructor
     * used when launching program
     */
    public ApplicationLoader() {
    }

    /**
     * main method to for starting the Application
     *
     * @param args parameter of main method used by Java loader
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * loads "Main.fxml" on Application launch
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws IOException IOException to throw if opening "Main.fxml" failed
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLoader.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
}
