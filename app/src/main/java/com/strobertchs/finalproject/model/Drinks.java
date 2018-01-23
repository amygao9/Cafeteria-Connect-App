package com.strobertchs.finalproject.model;

/**
 * Created by jenny on 2018-01-21.
 */

public class Drinks extends Product {
    /**
     * The type of drink - hot or cold
     */
    private String drinkType;

    /**
     * Constructor
     * @param pName
     * @param pUnitPrice
     * @param pImageID
     * @param pDrinkType
     */
    public Drinks(String pName, double pUnitPrice, int pImageID, String pDrinkType)
    {
        super(pName, pUnitPrice, pImageID);
        this.drinkType = pDrinkType;
    }

    /**
     * Gets the drink type
     * @return drinkType - the type of drink - hot or cold
     */
    public String getDrinkType() {
        return drinkType;
    }

    /**
     * Sets the drink type
     * @param drinkType
     */
    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    /**
     * Gives subdescription specific to drink - overrides method from super class
     * @return String that includes Product ID and drink type
     */
    public String getSubDescription()
    {
        return "ID: " + this.getProductID() + "/n" + this.drinkType;
    }
}
