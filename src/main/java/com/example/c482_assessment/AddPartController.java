package com.example.c482_assessment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.c482_assessment.ApplicationLoader.inventory;

/**
 * FXML Controller class
 * Controller for "AddPart.fxml"
 *
 * @author Sheldon Handler
 */
public class AddPartController implements Initializable {

    @FXML
    protected TextField idField;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField invField;
    @FXML
    protected TextField priceCostField;
    @FXML
    protected TextField maxField;
    @FXML
    protected TextField machineIdOrCompanyNameField;
    @FXML
    protected TextField minField;
    @FXML
    protected Label machineIdOrCompanyNameLabel;
    @FXML
    protected RadioButton inHouseRadioButton;
    @FXML
    protected RadioButton outsourcedRadioButton;
    @FXML
    protected ToggleGroup toggle;
    @FXML
    protected Button saveButton;
    @FXML
    protected Button cancelButton;

    /**
     * default constructor
     * used for FXML Controller when running "AddPart.fxml"
     */
    public AddPartController() {
    }

    /**
     * runs when AddPartController is started
     * sets the value of idField based on the id of the last "Part"
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting idField
        if (inventory.getAllParts().isEmpty()) {
            idField.setText(String.valueOf(1));
        } else {
            idField.setText(String.valueOf(inventory.getAllParts().get(inventory.getAllParts().size() - 1).getId() + 1));
        }
    }


    /**
     * runs when inHouseRadioButton is pressed
     * selects the inHouseRadioButton and deselects the outsourcedRadioButton
     */
    @FXML
    protected void onInHouseButtonClick() {
        toggle.selectToggle(inHouseRadioButton);
        machineIdOrCompanyNameLabel.setText("Machine ID");
    }

    /**
     * runs when outsourcedRadioButton is pressed
     * selects outsourcedRadioButton and deselects inHouseRadioButton
     */
    @FXML
    protected void onOutsourcedButtonClick() {
        toggle.selectToggle(outsourcedRadioButton);
        machineIdOrCompanyNameLabel.setText("Company Name");
    }

    /**
     * if the input is invalid, displays the appropriate error Popup
     * otherwise saves all the data entered to inventory::allParts then replaces current window with "Main.fxml"
     *
     * @param actionEvent ActionEvent to pass for replacing current window
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent actionEvent) {

        ConstraintChecker constraintChecker = new ConstraintChecker();

        // checking if fields filled out in "AddPart.FXML" meet full criteria to be saved
        if (idField.getText().isEmpty() || nameField.getText().isEmpty() || priceCostField.getText().isEmpty() || invField.getText().isEmpty() || minField.getText().isEmpty() || maxField.getText().isEmpty() || machineIdOrCompanyNameField.getText().isEmpty()) {
            // returning error message requesting all fields be filled out
            constraintChecker.nullFieldError();
        } else if (!constraintChecker.toInteger(idField.getText()) || !constraintChecker.toInteger(invField.getText()) || !constraintChecker.toDouble(priceCostField.getText()) || !constraintChecker.toInteger(minField.getText()) || !constraintChecker.toInteger(maxField.getText())) {
            // returning error message specifying field type contraints
            constraintChecker.saveErrorBox();
        } else if (inHouseRadioButton.isSelected() && !constraintChecker.toInteger(machineIdOrCompanyNameField.getText())) {
            constraintChecker.saveErrorBox();
        } else if (!constraintChecker.numericalConstraintChecker(Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()))) {
            // returning error message specifying numerical contraints
            constraintChecker.numericalErrorBox();
        } else {

            // checking which RadioButton is selected and creating their respective class to save to Inventory::allParts
            if (inHouseRadioButton.isSelected()) {
                // creating InHouse object and adding it to Inventory::allParts
                InHouse inHouse = new InHouse(Integer.parseInt(idField.getText()), nameField.getText(), Double.parseDouble(priceCostField.getText()), Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()), Integer.parseInt(machineIdOrCompanyNameField.getText()));
                inventory.addPart(inHouse);
                // returning to "Main.fxml"
                constraintChecker.toMainWindow(actionEvent);
            } else if (outsourcedRadioButton.isSelected()) {
                // creating Outsourced object and saving it to Inventory::allParts
                Outsourced outsourced = new Outsourced(Integer.parseInt(idField.getText()), nameField.getText(), Double.parseDouble(priceCostField.getText()), Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()), machineIdOrCompanyNameField.getText());
                inventory.addPart(outsourced);
                // returning to "Main.fxml"
                constraintChecker.toMainWindow(actionEvent);
            } else {
                throw new RuntimeException();
            }
        }

    }


    /**
     * replaces current window with "Main.fxml"
     *
     * @param actionEvent ActionEvent to pass for replacing current window
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent actionEvent) {
        // returning to "Main.fxml"
        ConstraintChecker constraintChecker = new ConstraintChecker();
        constraintChecker.toMainWindow(actionEvent);
    }
}
