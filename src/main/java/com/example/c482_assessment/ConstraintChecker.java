package com.example.c482_assessment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.ButtonType.OK;

/**
 * contains methods with code to load some FXML windows and dialogs
 * contains methods to check if certain constraints are met from parameters passed
 *
 * @author Sheldon Handler
 */
// FIXME: 7/31/2022 seperate methods that are called to load FXML windows into a new class
public class ConstraintChecker {


    /**
     * default constructor
     * used when instantiating ConstraintChecker
     */
    public ConstraintChecker() {
    }

    /**
     * checks whether min, max, and inv quantity constraints are met and returns result
     *
     * @param stock the inventory amount
     * @param min   the min amount
     * @param max   the max amount
     * @return whether the min, max, and inventory constraints are met by the specified parameters
     */
    public boolean numericalConstraintChecker(int stock, int min, int max) {

        return stock >= min && stock <= max;
    }

    /**
     * checks the given String can be expressed as an Integer
     *
     * @param string the String to check
     * @return boolean whether the string parameter can be converted to an Integer
     */
    public boolean toInteger(String string) {
        boolean integer;
        try {
            integer = true;
            Integer.parseInt(string);
        } catch (NumberFormatException numberFormatException) {
            integer = false;
            numberFormatException.printStackTrace();
        }
        return integer;
    }

    /**
     * checks whether String can be converted to a Double
     *
     * @param string String to check
     * @return boolean whether string is equivalent to a Double value
     */
    public boolean toDouble(String string) {
        boolean isDouble = true;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException numberFormatException) {
            isDouble = false;
            numberFormatException.printStackTrace();
        }
        return isDouble;
    }

    /**
     * creating popup FXML listing required types in each field of object Part
     */
    public void saveErrorBox() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SaveError.fxml"));
            Parent parent = fxmlLoader.load();
            SaveErrorController saveErrorController = fxmlLoader.getController();
            saveErrorController.textBox.setText("Cannot save data using input from fields.\n" + "Please enter the following types in each field:\n" + "\n Name: String \n Inv: Integer \n Price/Cost: Double \n Min: Integer \n Max: Integer \n Machine ID: Integer \n Company Name: String");

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();

            Button button = (Button) saveErrorController.dialogPane.lookupButton(OK);
            button.setOnAction(e -> stage.close());


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * displaying error dialog popup stating the contraints that were not met
     */
    public void numericalErrorBox() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SaveError.fxml"));
            Parent parent = fxmlLoader.load();
            SaveErrorController saveErrorController = fxmlLoader.getController();
            saveErrorController.textBox.setText("Cannot create product using input from fields.\n" + "\nInput fields must meet the following constraints." + "\n min ≤ Inv ≤ max");

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();

            Button button = (Button) saveErrorController.dialogPane.lookupButton(OK);
            button.setOnAction(e -> stage.close());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * creating popup dialog stating requirement for each field to have a value
     */
    public void nullFieldError() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SaveError.fxml"));
            Parent parent = fxmlLoader.load();
            SaveErrorController saveErrorController = fxmlLoader.getController();
            saveErrorController.textBox.setText("Cannot save data. \n All fields must be have valid data input into them.");

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();

            Button button = (Button) saveErrorController.dialogPane.lookupButton(OK);
            button.setOnAction(e -> stage.close());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * creating popup dialog to specify type requirement for each Product field
     */
    public void productSaveError() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SaveError.fxml"));
            Parent parent = fxmlLoader.load();
            SaveErrorController saveErrorController = fxmlLoader.getController();
            saveErrorController.textBox.setText("Cannot create product using input from fields.\n" + "Please enter the following types in each field:\n" + "\n Name: String \n Inv: Integer \n Price/Cost: Double \n Min: Integer \n Max: Integer");

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.show();

            Button button = (Button) saveErrorController.dialogPane.lookupButton(OK);
            button.setOnAction(e -> stage.close());

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException(ioException);
        }
    }

    /**
     * replaces current window with "Main.fxml" using actionEvent parameter
     *
     * @param actionEvent actionEvent passed when calling method to use for returning to "Main.fxml"
     */
    public void toMainWindow(ActionEvent actionEvent) {

        // replacing existing window with "Main.fxml" window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Main.fxml"));
            fxmlLoader.load();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Main");
            Parent root = fxmlLoader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}