package com.example.c482_assessment;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.c482_assessment.ApplicationLoader.inventory;
import static com.example.c482_assessment.MainController.*;

/**
 * FXML Controller class
 * Controller for "ModifyPart.fxml"
 *
 * @author Sheldon Handler
 */
public class ModifyPartController implements Initializable {

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
    protected Button saveButton;
    @FXML
    protected Button cancelButton;
    @FXML
    protected ToggleGroup toggle;

    /**
     * default constructor
     * used as FXML Controller when running "ModifyPart.fxml"
     */
    public ModifyPartController() {
    }

    /**
     * code to run for initialize
     * prefills each field and selects the Toggle for RadioButton to match the information of current public static Part part
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idField.setText(String.valueOf(part.getId()));
        nameField.setText(part.getName());
        invField.setText(String.valueOf(part.getStock()));
        priceCostField.setText(String.valueOf(part.getPrice()));
        minField.setText(String.valueOf(part.getMin()));
        maxField.setText(String.valueOf(part.getMax()));
        if (inHouse != null && outsourced == null) {
            machineIdOrCompanyNameField.setText(String.valueOf(inHouse.getMachineId()));
            toggle.selectToggle(inHouseRadioButton);
        } else if (inHouse == null && outsourced != null) {
            machineIdOrCompanyNameField.setText(outsourced.getCompanyName());
            toggle.selectToggle(outsourcedRadioButton);
        }
    }

    /**
     * runs when InHouseRadioButton is pressed
     * selectes InHouseRadioButton and deselects OutsourcedRadioButton
     */
    @FXML
    protected void onInHouseButtonClick() {
        toggle.selectToggle(inHouseRadioButton);
        machineIdOrCompanyNameLabel.setText("Machine ID");
    }

    /**
     * runs when OutsourcedRadioButton is pressed
     * selects OutsourcedRadioButton and deselects InHouseRadioButton
     */
    @FXML
    protected void onOutsourcedButtonClick() {
        toggle.selectToggle(outsourcedRadioButton);
        machineIdOrCompanyNameLabel.setText("Company Name");
    }

    /**
     * checks that all fields entered are valid and meet correct constraints and if so then it replaces current window with "Main.fxml"
     * otherwise it loads Popup dialog box showing what needs to be modified for the data entered to be valid
     *
     * @param actionEvent ActionEvent to pass if replacing current window
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent actionEvent) {

        ConstraintChecker constraintChecker = new ConstraintChecker();

        boolean found = false;
        int temp = 0;

        if (idField.getText().isEmpty() || nameField.getText().isEmpty() || priceCostField.getText().isEmpty() || invField.getText().isEmpty() || minField.getText().isEmpty() || maxField.getText().isEmpty() || machineIdOrCompanyNameField.getText().isEmpty()) {
            constraintChecker.nullFieldError();
        } else if (!constraintChecker.toInteger(idField.getText()) || !constraintChecker.toInteger(invField.getText()) || !constraintChecker.toDouble(priceCostField.getText()) || !constraintChecker.toInteger(minField.getText()) || !constraintChecker.toInteger(maxField.getText())) {
            constraintChecker.saveErrorBox();
        } else if (inHouseRadioButton.isSelected() && !constraintChecker.toInteger(machineIdOrCompanyNameField.getText())) {
            constraintChecker.saveErrorBox();
        } else if (!constraintChecker.numericalConstraintChecker(Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()))) {
            constraintChecker.numericalErrorBox();
        } else {

            if (outsourcedRadioButton.isSelected()) {
                Outsourced outsourced = new Outsourced(Integer.parseInt(idField.getText()), nameField.getText(), Double.parseDouble(priceCostField.getText()), Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()), machineIdOrCompanyNameField.getText());

                // finding index of part
                ObservableList<Part> allParts = inventory.getAllParts();
                for (int i = 0; i < allParts.size(); i++) {
                    if (allParts.get(i) == MainController.part) {
                        temp = i;
                        found = true;
                    }
                }

                // updating part if index found
                if (found) {
                    inventory.updatePart(temp, outsourced);
                }

            } else if (inHouseRadioButton.isSelected()) {

                InHouse inHouse = new InHouse(Integer.parseInt(idField.getText()), nameField.getText(), Double.parseDouble(priceCostField.getText()), Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()), Integer.parseInt(machineIdOrCompanyNameField.getText()));

                // finding index of part
                ObservableList<Part> allParts = inventory.getAllParts();
                for (int i = 0; i < allParts.size(); i++) {
                    if (allParts.get(i) == MainController.part) {
                        temp = i;
                        found = true;
                    }
                }

                if (found) {
                    inventory.updatePart(temp, inHouse);
                }
            }

            // returning to "Main.fxml" window
            constraintChecker.toMainWindow(actionEvent);
        }
    }

    /**
     * replaces current window with "Main.fxml"
     *
     * @param actionEvent ActionEvent passed to use for replacing current window
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent actionEvent) {

        ConstraintChecker constraintChecker = new ConstraintChecker();

        // returning to "Main.fxml" window
        constraintChecker.toMainWindow(actionEvent);
    }
}
