package com.strobertchs.finalproject.model;

import com.google.firebase.database.Exclude;

/**
 * Created by jenny on 2018-01-21.
 */

public class Food extends Product {

    /**
     * Ingredients in the product
     */
    private String ingredients;

    public Food(){}

    /**
     * Constructor
     * @param pName
     * @param pUnitPrice
     * @param pImageID
     * @param pIngredients
     */
    public Food(String pName, double pUnitPrice, int pImageID, String pIngredients)
    {
        super(pName, pUnitPrice, pImageID);
        this.ingredients = pIngredients;
    }

    /**
     * Gets product's ingredients
     * @return ingredients - the product's ingredients
     */
    public String getIngredients()
    {
        return ingredients;
    }

    /**
     * Sets the product's ingredients
     * @param ingredients - the product's new ingredients
     */
    public void setIngredients(String ingredients)
    {
        this.ingredients = ingredients;
    }

    /**
     * Overrides getSubDescription in superclass
     * @return Product ID and the main ingredients
     */
    @Exclude
    public String getSubDescription()
    {
        return "ID: " + this.getProductID() + "\nMain ingredients include: " + ingredients;
    }
}
