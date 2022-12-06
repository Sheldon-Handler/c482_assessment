package com.example.c482_assessment;

/**
 * Outsourced member class that extends abstract class Part
 *
 * @author Sheldon Handler
 * @see com.example.c482_assessment.Part
 */
public class Outsourced extends Part {

    /**
     * companyName variable of Outsourced
     */
    private String companyName;

    /**
     * constructor for class Outsourced
     *
     * @param id          id of Part
     * @param name        name of Part
     * @param price       price of Part
     * @param stock       current inventory of Part
     * @param min         minimum required inventory of Part
     * @param max         maximum permitted inventory of Part
     * @param companyName companyName variable of subClass Outsourced
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * gets the name of variable companyName in this Outsourced object
     *
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }


    /**
     * sets the companyName variable for this Instance of Outsourced
     *
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
