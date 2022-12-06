package com.example.c482_assessment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * for Popup Dialog "DeletePartError.fxml"
 *
 * @author Sheldon Handler
 */
public class DeletePartErrorController implements Initializable {

    @FXML
    protected DialogPane dialogPane;
    @FXML
    protected Text textBox;

    /**
     * default constructor
     * used as FXML Controller for running "DeletePartError.fxml"
     */
    public DeletePartErrorController() {
    }

    /**
     * code to run when DeletePartErrorController is called
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialogPane.setContent(textBox);
    }
}
