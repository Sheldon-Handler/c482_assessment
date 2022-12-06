package com.example.c482_assessment;


/**
 * InHouse subclass of Part
 *
 * @author Sheldon Handler
 */
public class InHouse extends Part {

    private int machineId;

    /**
     * constructor for InHouse
     *
     * @param id        id of Part
     * @param name      name of Part
     * @param price     price of Part
     * @param stock     inventory of Part
     * @param min       minimum stock of Part
     * @param max       maximum stock of Part
     * @param machineId machineId variable of this Part member class InHouse
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * gets the machineId
     *
     * @return machineId to return
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * sets the machineId
     *
     * @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}
