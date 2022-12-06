package com.example.c482_assessment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Vector;

/**
 * Product class
 *
 * @author Sheldon Handler
 */
public class Product {

    private final ObservableList<Part> associatedParts = FXCollections.observableList(new Vector<Part>());
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Product class for each Product stored in (ObservableList) Inventory::allProducts
     *
     * @param id    id of Product
     * @param name  name of Product
     * @param price price of Product
     * @param stock inventory of Product
     * @param min   minimum inventory of Product required
     * @param max   maximum inventory of Product required
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    /**
     * returns the id of this product
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of this product
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns the name of this product
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of this product
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns price of Product
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets price of this Product
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * returns current quantity of this Product
     *
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * sets current quantity of this Product
     *
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * returns minimum quantity of this Product required
     *
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * sets minimum quantity of this Product required
     *
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * returns maximum quantity of Product allowed
     *
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * sets maximum quantity of Product allowed
     *
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * sets minimum quantity required
     *
     * @param part the associatedPart to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * deletes specified Part from associatedParts
     *
     * @param selectedAssociatedPart the associatedPart to remove
     */
    public void deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * returns all ObservableList of every Part associated with this Product
     *
     * @return associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
