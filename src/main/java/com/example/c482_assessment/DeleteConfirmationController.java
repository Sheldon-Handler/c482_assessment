package com.example.c482_assessment;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example.c482_assessment.ApplicationLoader.inventory;

/**
 * FXML Controller class
 * Controller for "DeleteConfirmation.fxml"
 *
 * @author Sheldon Handler
 */
public class DeleteConfirmationController {

    @FXML
    protected DialogPane dialogPane;
    @FXML
    protected Text textBox;

    /**
     * default constructor
     */
    public DeleteConfirmationController() {
    }

    /**
     * Overloaded method for Part class
     * method to run using lambda when YES is clicked to delete Part and then close the window
     *
     * @param stage Stage to pass for closing
     * @param part  Part to pass for deletion
     */
    public void onYesButtonClicked(Stage stage, Part part) {
        inventory.deletePart(part);
        stage.close();
    }

    /**
     * Overloaded method for Product class
     * method to run using lambda when YES is clicked to delete Product and then close the window
     *
     * @param stage   Stage to pass for closing
     * @param product Product to pass for deletion
     */
    public void onYesButtonClicked(Stage stage, Product product) {
        inventory.deleteProduct(product);
        stage.close();
    }
}
