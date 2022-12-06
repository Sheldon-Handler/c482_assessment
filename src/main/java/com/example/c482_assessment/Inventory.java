package com.example.c482_assessment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Vector;

/**
 * Inventory class
 * stores Inventory data in class Instance
 *
 * @author Sheldon Handler
 */
// FIXME: 7/31/2022 have all the data for class inventory save to file or database everytime anything is added, modified, or deleted from inventory
public class Inventory {

    private final ObservableList<Part> allParts = FXCollections.observableList(new Vector<Part>());
    private final ObservableList<Product> allProducts = FXCollections.observableList(new Vector<Product>());

    /**
     * default constructor
     * used when instantiation an Inventory variable
     */
    public Inventory() {
    }

    /**
     * adds Part to allParts
     *
     * @param newPart new part to add
     */
    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * adds Product newProduct to allProducts
     *
     * @param newProduct Product to add
     */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * finds Part with id matching partId
     *
     * @param partId the ID of the part to search for
     * @return returns Part with matching partId
     */
    public Part lookupPart(int partId) {

        Part part = null;
        for (int i = 0; i < getAllParts().size(); i++) {
            if (getAllParts().get(i).getId() == partId) {
                part = getAllParts().get(i);
            }
        }
        return part;
    }


    /**
     * returns list of Parts with matching subString of partName
     *
     * @param partName subString of part to search for
     * @return ObservableList of Part objects to return containing matches
     */
    public ObservableList<Part> lookupPart(String partName) {

        // creating ObservableList<Part> to store matching parts
        ObservableList<Part> matchingParts = FXCollections.observableList(new Vector<Part>());

        // copying Parts with matching partNames into matchingProduct variable
        for (int i = 0; i < getAllParts().size(); i++) {
            if (getAllParts().get(i).getName().contains(partName)) {
                matchingParts.add(getAllParts().get(i));
            }
        }
        return matchingParts;
    }

    /**
     * return Product with id matching int productId
     *
     * @param productId productId to lookup
     * @return Product with matching productId
     */
    public Product lookupProduct(int productId) {

        // return null if allProducts is empty
        // searching "ObservableList<Product> allProducts" variable for matching ID
        Product product = null;
        for (int i = 0; i < getAllProducts().size(); i++) {
            if (getAllProducts().get(i).getId() == productId) {
                product = allProducts.get(i);
            }
        }
        return product;
    }


    /**
     * returns list of Products that contain productName as subString
     *
     * @param productName name of Product to lookup
     * @return products with matching name
     */
    public ObservableList<Product> lookupProduct(String productName) {

        // creating filteredList to store matching products
        ObservableList<Product> matchingProduct = FXCollections.observableList(new Vector<Product>());

        // copying Products with matching productName into matchingProduct variable
        for (int i = 0; i < getAllProducts().size(); i++) {
            if (getAllProducts().get(i).getName().contains(productName)) {
                matchingProduct.add(getAllProducts().get(i));
            }
        }
        return matchingProduct;
    }

    /**
     * replaces Part at specified "index" with "newPart"
     *
     * @param index   index of Part to replace
     * @param newPart Part to insert as replacement
     */
    public void updatePart(int index, Part newPart) {

        // checking if selected index exists
        if (allParts.get(index) != null) {


            // find out if the Part is in any associatedPart
            for (int i = 0; i < getAllProducts().size(); i++) {

                // if the Part is in any associatedPart then replace it with newPart
                if (getAllProducts().get(i).getAllAssociatedParts().contains(getAllParts().get(i))) {
                    allProducts.get(i).getAllAssociatedParts().set(i, newPart);
                }
            }

            // replacing Part at specified index with newPart
            allParts.set(index, newPart);

        } else {
            throw new RuntimeException("Index not found");
        }
    }


    /**
     * replaces Product at index with newProduct
     *
     * @param index      index of Product to replace
     * @param newProduct Product to insert as replacement
     */
    public void updateProduct(int index, Product newProduct) {

        // checking if selected index exists
        if (allProducts.get(index) != null) {
            // replacing Product at specified index with newProduct
            allProducts.set(index, newProduct);
        } else {
            throw new RuntimeException("Index not found");
        }
    }

    /**
     * deletes selectedPart from allParts
     *
     * @param selectedPart part to delete from allParts
     * @return boolean whether part was found and deleted
     */
    public boolean deletePart(Part selectedPart) {


        // deleting selectedPart from "ObservableList<Part> allParts" if it is found
        boolean found = false;
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * deletes Product selectedProduct from allProducts
     *
     * @param selectedProduct product to remove if found
     * @return whether product was found and deleted
     */
    public boolean deleteProduct(Product selectedProduct) {

        // deleting selectedProduct from "ObservableList<Product> allProducts" if it is found
        boolean found = false;
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }


    /**
     * returns ObservableList of allParts
     *
     * @return allParts
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * returns ObservableList of allProducts
     *
     * @return allProducts
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
