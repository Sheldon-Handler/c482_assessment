package com.example.c482_assessment;

/**
 * Supplied class Part.java
 *
 * @author Sheldon Handler
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * constructor for abstract class Part
     *
     * @param id    id of Part
     * @param name  name of Part
     * @param price price of Part
     * @param stock stock of part
     * @param min   min of Part
     * @param max   max of Part
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * gets id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * sets id
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * gets name
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets price
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets price
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * gets stock
     *
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * sets stock
     *
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * gets min
     *
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * sets min
     *
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * gets max
     *
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * sets max
     *
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}