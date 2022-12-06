package com.example.c482_assessment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.c482_assessment.ApplicationLoader.inventory;
import static javafx.application.Platform.exit;
import static javafx.scene.control.ButtonType.*;

/**
 * FXML Controller class
 *
 * @author Sheldon Handler
 */
public class MainController implements Initializable {

    /**
     * static Part variable to be used by multiple classes
     */
    public static Part part;
    /**
     * InHouse static variable to be used between multiple classes
     */
    public static InHouse inHouse;
    /**
     * static Outsourced variable to be used between multiple classes
     */
    public static Outsourced outsourced;
    /**
     * static Product variable to be used between multiple classes
     */
    public static Product product;
    @FXML
    protected TableView<Part> partsTableInMainForm;
    @FXML
    protected TableColumn<Part, Integer> partId;
    @FXML
    protected TableColumn<Part, String> partName;
    @FXML
    protected TableColumn<Part, Integer> inventoryLevel;
    @FXML
    protected TableColumn<Part, Double> priceCostPerUnit;
    @FXML
    protected TableView<Product> productsTableInMainForm;
    @FXML
    protected TableColumn<Product, Integer> productId;
    @FXML
    protected TableColumn<Product, String> productName;
    @FXML
    protected TableColumn<Product, Integer> productInventoryLevel;
    @FXML
    protected TableColumn<Product, Double> productPriceCostPerUnit;
    @FXML
    protected Button partsAddButton;
    @FXML
    protected Button partsModifyButton;
    @FXML
    protected Button partsDeleteButton;
    @FXML
    protected TextField partsSearchBox;
    @FXML
    protected Button partsSearchButton;
    @FXML
    protected Button productsAddButton;
    @FXML
    protected Button productsModifyButton;
    @FXML
    protected Button productsDeleteButton;
    @FXML
    protected TextField productsSearchBox;
    @FXML
    protected Button productsSearchButton;
    @FXML
    protected Button exitButton;

    /**
     * default constructor
     * used to open FXML Controller for "Main.fxml"
     */
    public MainController() {
    }

    /**
     * sets the TableView items for partsTableInMainForm and productsTabkeInMainForm
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // setting partsTableInMainForm
        partsTableInMainForm.setItems(inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        // setting productsTableInMainForm
        productsTableInMainForm.setItems(inventory.getAllProducts());
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * open  the "Add Part" window for selected part using "AddPart.fxml"
     */
    @FXML
    protected void onPartsAddButtonClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddPart.fxml"));
            fxmlLoader.load();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Add Part");
            Parent root = fxmlLoader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * open  the "Modify Part" window for selected part using "ModifyPart.fxml"
     * sets the static variable inHouse or outsourced to the selectedItem
     *
     * @param actionEvent ActionEvent passed from "onAction" for partsModifyButton
     */
    @FXML
    protected Part onPartsModifyButtonClick(ActionEvent actionEvent) {

        part = partsTableInMainForm.getSelectionModel().getSelectedItem();

        // finding class type of part
        if (part.getClass() == InHouse.class) {
            outsourced = null;
            inHouse = new InHouse(part.getId(), part.getName(), part.getPrice(), part.getStock(), part.getMin(), part.getMax(), ((InHouse) part).getMachineId());
        } else if (part.getClass() == Outsourced.class) {
            inHouse = null;
            outsourced = new Outsourced(part.getId(), part.getName(), part.getPrice(), part.getStock(), part.getMin(), part.getMax(), ((Outsourced) part).getCompanyName());
        } else {
            throw new RuntimeException();
        }

        // opening "ModifyPart.fxml"
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ModifyPart.fxml"));
            fxmlLoader.load();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Part");
            Parent root = fxmlLoader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return part;
    }

    /**
     * method to run when partsDeleteButton is clicked
     * if the item selected in partsTableInMainForm is associated with a Product a Popup error will display showing all Products associated with this Part
     * if the item selected in partsTableInMainForm is not associated with any part then the item is deleted
     */
    @FXML
    protected void onPartsDeleteButtonClick() {

        part = partsTableInMainForm.getSelectionModel().getSelectedItem();

        // finding part is associated with any product
        String productsAssociatedWithThisPart = "";

        for (int i = 0; i < inventory.getAllProducts().size(); i++) {
            if (inventory.getAllProducts().get(i).getAllAssociatedParts().contains(part)) {
                productsAssociatedWithThisPart += ("\n" + inventory.getAllProducts().get(i).getName());
            }
        }

        if (productsAssociatedWithThisPart == "") {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteConfirmation.fxml"));
                Parent parent = fxmlLoader.load();
                DeleteConfirmationController deleteConfirmationController = fxmlLoader.getController();
                deleteConfirmationController.textBox.setText("Are you sure you want to delete Part '" + part.getName() + "' from the Inventory list."); //

                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("Confirm Delete");
                stage.show();

                Button yesButton = (Button) deleteConfirmationController.dialogPane.lookupButton(YES);
                yesButton.setOnAction(e -> deleteConfirmationController.onYesButtonClicked(stage, part));

                Button noButton = (Button) deleteConfirmationController.dialogPane.lookupButton(NO);
                noButton.setOnAction(e -> stage.close());

            } catch (IOException ioException) {
                ioException.printStackTrace();
                throw new RuntimeException(ioException);
            }

            // updating PartsTableInMainForm to match new deletion of Part list
            partsTableInMainForm.setItems(inventory.getAllParts());

        } else {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeletePartError.fxml"));
                Parent parent = fxmlLoader.load();
                DeletePartErrorController deletePartErrorController = fxmlLoader.getController();
                deletePartErrorController.textBox.setText("Cannot delete part that is associated with a product.\n" + "Products associated with this part:\n" + productsAssociatedWithThisPart);

                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("Error");
                stage.show();

                Button button = (Button) deletePartErrorController.dialogPane.lookupButton(OK);
                button.setOnAction(e -> stage.close());

            } catch (IOException ioException) {
                ioException.printStackTrace();
                throw new RuntimeException(ioException);
            }
        }


    }

    /**
     * replaces the current window with "AddProduct.fxml"
     *
     * @param actionEvent actionEvent to pass for opening "AddProduct.fxml"
     */
    @FXML
    protected void onProductsAddButtonClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddProduct.fxml"));
            fxmlLoader.load();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Add Product");
            Parent root = fxmlLoader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    /**
     * replaces current window with "ModifyProduct.fxml"
     *
     * @param actionEvent actionEvent to pass for replacing current window
     */
    @FXML
    public void onProductsModifyButtonClick(ActionEvent actionEvent) {

        product = productsTableInMainForm.getSelectionModel().getSelectedItem();

        // opening "ModifyProduct.fxml"
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ModifyProduct.fxml"));
            fxmlLoader.load();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Product");
            Parent root = fxmlLoader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * deletes the item selected in productsTableInMainForm from inventory
     * updates productsTableInMainForm to reflect the removal from invntory
     */
    @FXML
    protected void onProductsDeleteButtonClick() {
        product = productsTableInMainForm.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteConfirmation.fxml"));
            Parent parent = fxmlLoader.load();
            DeleteConfirmationController deleteConfirmationController = fxmlLoader.getController();
            deleteConfirmationController.textBox.setText("Are you sure you want to delete Product '" + product.getName() + "' from the Inventory list."); //

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Confirm Delete");
            stage.show();

            Button yesButton = (Button) deleteConfirmationController.dialogPane.lookupButton(YES);
            yesButton.setOnAction(e -> deleteConfirmationController.onYesButtonClicked(stage, product));

            Button noButton = (Button) deleteConfirmationController.dialogPane.lookupButton(NO);
            noButton.setOnAction(e -> stage.close());

        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException(ioException);
        }

        // updating Product TableView to match deletion
        productsTableInMainForm.setItems(inventory.getAllProducts());
    }

    /**
     * exits the program when exitButton is clicked
     */
    @FXML
    protected void onExitButtonClick() {
        exit();
    }

    /**
     * displays search results in partsTableInMainForm
     * search results are based on what is entered in partsSearchBox
     */
    // FIXME: 7/31/2022 make search results case insensitive relative to search input text
    @FXML
    protected void onPartsSearchButtonClick() {

        ConstraintChecker constraintChecker = new ConstraintChecker();

        ObservableList<Part> partObservableList = FXCollections.observableList(new ArrayList<>());


        if (partsSearchBox.getText() == null) {
            partObservableList.clear();
            partsTableInMainForm.setItems(inventory.getAllParts());
        } else {

            if (constraintChecker.toInteger(partsSearchBox.getText())) {
                if (inventory.lookupPart(Integer.parseInt(partsSearchBox.getText())) != null) {
                    partObservableList.add(inventory.lookupPart(Integer.parseInt(partsSearchBox.getText())));
                }
            }

            if (inventory.lookupPart(partsSearchBox.getText()) != null) {
                partObservableList.addAll(inventory.lookupPart(partsSearchBox.getText()));
            }

            partsTableInMainForm.setItems(partObservableList);
        }
    }


    /**
     * displays search results in partsTableInMainForm
     * search results are based on what is entered in partsSearchBox
     */
    // FIXME: 7/31/2022 make search results case insensitive relative to search input text
    @FXML
    protected void onProductsSearchButtonClick() {

        ConstraintChecker constraintChecker = new ConstraintChecker();

        ObservableList<Product> productObservableList = FXCollections.observableList(new ArrayList<>());

        if (productsSearchBox.getText() == null) {
            productObservableList.clear();
            productsTableInMainForm.setItems(inventory.getAllProducts());
        } else {

            if (constraintChecker.toInteger(productsSearchBox.getText())) {
                if (inventory.lookupProduct(Integer.parseInt(productsSearchBox.getText())) != null) {
                    productObservableList.add(inventory.lookupProduct(Integer.parseInt(productsSearchBox.getText())));
                }
            }

            if (inventory.lookupProduct(productsSearchBox.getText()) != null) {
                productObservableList.addAll(inventory.lookupProduct(productsSearchBox.getText()));
            }

            productsTableInMainForm.setItems(productObservableList);
        }
    }
}
