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

import static com.example.c482_assessment.ApplicationLoader.inventory;
import static com.example.c482_assessment.MainController.product;

/**
 * FXML Controller class
 *
 * @author Sheldon Handler
 * opens "ModifyProduct.fxml" using selectedProduct
 */
public class ModifyProductController implements Initializable {

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
    protected TableView<Part> partsTable;
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
     * used as FXML Controller when running "ModifyProduct.fxml"
     */
    public ModifyProductController() {
    }

    /**
     * runs when this class is loaded
     * prefills fields with data of the Product to modify
     * prefills associatedPartsTable using getAssociatedParts() on current Product instance
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting initial values of product to modify
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        invField.setText(String.valueOf(product.getStock()));
        priceCostField.setText(String.valueOf(product.getPrice()));
        minField.setText(String.valueOf(product.getMin()));
        maxField.setText(String.valueOf(product.getMax()));

        // getting partsTable from inventory
        partsTable.setItems(inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        // getting associatedPartsTable of product
        associatedPartsTable.setItems(product.getAllAssociatedParts());
        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * runs when saveButton is pressed
     * replaces current window with "Main.fxml"
     *
     * @param actionEvent actionEvent to pass to "Stage" class
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent actionEvent) {

        ConstraintChecker constraintChecker = new ConstraintChecker();
        try {

            // copying entered text into variables that can be saved
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int stock = Integer.parseInt(invField.getText());
            double price = Double.parseDouble(priceCostField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());

            if (!constraintChecker.numericalConstraintChecker(stock, min, max)) {
                constraintChecker.numericalErrorBox();
            } else {

                // creating Product using fields
                Product newProduct = new Product(id, name, price, stock, min, max);

                // adding associatedParts to product if associatedPartsTable is not empty
                if (!associatedPartsTable.getItems().isEmpty()) {
                    for (int i = 0; i < associatedPartsTable.getItems().size(); i++) {
                        newProduct.addAssociatedPart(associatedPartsTable.getItems().get(i));
                    }
                }

                // saving product fields to inventory
                inventory.updateProduct(inventory.getAllProducts().indexOf(product), newProduct);

                constraintChecker.toMainWindow(actionEvent);
            }
        } catch (RuntimeException runtimeException) {

            constraintChecker.productSaveError();
        }
    }


    /**
     * runs when cancelButton is pressed
     * replaces current window with "Main.fxml"
     *
     * @param actionEvent actionEvent variable to pass to "Stage" class
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent actionEvent) {

        ConstraintChecker constraintChecker = new ConstraintChecker();
        constraintChecker.toMainWindow(actionEvent);

    }

    /**
     * adds the currently selected item in partsTable to associatedPartsTable
     */
    // FIXME: 7/31/2022 make associatedParts automatically sort items based on "Part ID" every time a Part is added
    @FXML
    protected void onAddAssociatedPartButtonClick() {
        // adds selectedItem to Vector<Part> associatedParts
        if (!(associatedPartsTable.getItems().contains(partsTable.getSelectionModel().getSelectedItem()))) {

            if (partsTable.getSelectionModel().getSelectedItem() != null) {
                product.addAssociatedPart((partsTable.getSelectionModel().getSelectedItem()));
                associatedPartsTable.setItems(product.getAllAssociatedParts());
            }
        }
    }

    /**
     * deletes the selected item from associatedPartsTable
     */
    @FXML
    protected void onRemoveAssociatedPartButtonClick() {
        if (associatedPartsTable.getSelectionModel().getSelectedItem() != null) {

            // deleting selected Part from list of associatedParts
            product.deleteAssociatedPart(associatedPartsTable.getSelectionModel().getSelectedItem());

            // updating TableView of associatedParts
            associatedPartsTable.setItems(product.getAllAssociatedParts());
        }
    }

    /**
     * executes when partsSearchButton in "ModifyProduct.fxml" is clicked
     * display ListView of parts whose partName contains the String sequence as part of their name or whose partId matches
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
