package com.example.c482_assessment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import static com.example.c482_assessment.ApplicationLoader.inventory;

/**
 * FXML Controller class
 * Controller for "AddProduct.fxml"
 *
 * @author Sheldon Handler
 */
public class AddProductController implements Initializable {

    /**
     * ListView to display parts added to associatedPartsTable
     */
    private final ObservableList<Part> associatedParts = FXCollections.observableList(new Vector<Part>());
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
    protected TextField minField;
    @FXML
    protected TextField partsSearchBox;
    @FXML
    protected Button partsSearchButton;
    @FXML
    protected TableView<Part> partsTable = new TableView<>(inventory.getAllParts());
    @FXML
    protected TableView<Part> associatedPartsTable;
    @FXML
    protected TableColumn<Part, Integer> partId;
    @FXML
    protected TableColumn<Part, String> partName;
    @FXML
    protected TableColumn<Part, Integer> inventoryLevel;
    @FXML
    protected TableColumn<Part, Double> priceCostPerUnit;
    @FXML
    protected Button addAssociatedPartButton;
    @FXML
    protected TableColumn<Part, Integer> associatedPartId;
    @FXML
    protected TableColumn<Part, String> associatedPartName;
    @FXML
    protected TableColumn<Part, Integer> associatedPartInventoryLevel;
    @FXML
    protected TableColumn<Part, Double> associatedPartPriceCostPerUnit;
    @FXML
    protected Button removeAssociatedPartButton;
    @FXML
    protected Button saveButton;
    @FXML
    protected Button cancelButton;

    /**
     * default constructor
     * used when running "AddProduct.fxml"
     */
    public AddProductController() {
    }

    /**
     * initial values to set upon loading "AddProduct.fxml"
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));


        // setting idField
        if (inventory.getAllProducts().isEmpty()) {
            idField.setText(String.valueOf(1));
        } else {
            idField.setText(String.valueOf(inventory.getAllProducts().get(inventory.getAllProducts().size() - 1).getId() + 1));
        }
    }

    /**
     * method to execute when Save button is clicked
     *
     * @param actionEvent actionEvent passed from JavaFX Button click
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent actionEvent) {

        ConstraintChecker constraintChecker = new ConstraintChecker();

        // checking contraints
        if (idField.getText().isBlank() || nameField.getText().isBlank() || invField.getText().isBlank() || priceCostField.getText().isBlank() || minField.getText().isBlank() || maxField.getText().isBlank()) {
            constraintChecker.nullFieldError();
        } else if (!constraintChecker.toInteger(idField.getText()) || !constraintChecker.toInteger(invField.getText()) || !constraintChecker.toDouble(priceCostField.getText()) || !constraintChecker.toInteger(minField.getText()) || !constraintChecker.toInteger(maxField.getText())) {
            constraintChecker.saveErrorBox();
        } else if (!constraintChecker.numericalConstraintChecker(Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()))) {
            constraintChecker.numericalErrorBox();
        } else {
            // creating Product using fields
            Product product = new Product(Integer.parseInt(idField.getText()), nameField.getText(), Double.parseDouble(priceCostField.getText()), Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), Integer.parseInt(maxField.getText()));

            // adding associatedParts to product if associatedPartsTable is not empty
            if (!associatedParts.isEmpty()) {
                for (int i = 0; i < associatedPartsTable.getItems().size(); i++) {
                    product.addAssociatedPart(associatedParts.get(i));
                }
            }

            // saving product fields to inventory
            inventory.addProduct(product);

            // returning to "Main.fxml" window
            constraintChecker.toMainWindow(actionEvent);
        }
    }


    /**
     * runs when cancelButton is pressed
     * replaces current window with "Main.fxml"
     *
     * @param actionEvent ActionEvent variable to pass from Button press
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent actionEvent) {

        ConstraintChecker constraintChecker = new ConstraintChecker();
        // returning to "Main.fxml"
        constraintChecker.toMainWindow(actionEvent);
    }

    // FIXME: 7/31/2022 automatically sort items by "Part ID" when added to associatedParts

    /**
     * adds the item selected from partsTable to associatedParts
     */
    @FXML
    protected void onAddAssociatedPartButtonClick() {

        if (!(associatedPartsTable.getItems().contains(partsTable.getSelectionModel().getSelectedItem()))) {
            // add selectedItem to Vector<Part> associatedParts

            if (partsTable.getSelectionModel().getSelectedItem() != null) {
                associatedParts.add(partsTable.getSelectionModel().getSelectedItem());

                // set associatedPartsTable to match associatedParts
                associatedPartsTable.setItems(associatedParts);
            }
        }
    }

    /**
     * removes the item selected in associatedParts
     */
    @FXML
    protected void onRemoveAssociatedPartButtonClick() {
        // deleting selected Part from list of associatedParts
        associatedParts.remove(associatedPartsTable.getSelectionModel().getSelectedItem());

        // updating TableView of associatedParts
        associatedPartsTable.setItems(associatedParts);
    }

    // FIXME: 7/31/2022 fix the search results to show results without the input being case sensitive

    /**
     * searches partsTable based on input from partsSearchBox
     * displays results in partsTable
     */
    @FXML
    protected void onPartsSearchButtonClick() {
        ObservableList<Part> partObservableList = FXCollections.observableList(new ArrayList<>());

        if (partsSearchBox.getText() == null) {
            partObservableList.clear();
            partsTable.setItems(inventory.getAllParts());
        } else {
            for (int i = 0; i < inventory.getAllParts().size(); i++) {
                if (inventory.getAllParts().get(i).getName().contains(partsSearchBox.getText()) || String.valueOf(inventory.getAllParts().get(i).getId()).equals(partsSearchBox.getText())) {
                    partObservableList.add(inventory.getAllParts().get(i));
                }
            }
            partsTable.setItems(partObservableList);
        }
    }
}
