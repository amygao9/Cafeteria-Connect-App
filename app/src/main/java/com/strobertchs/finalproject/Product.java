package com.strobertchs.finalproject;
import java.text.NumberFormat;

public class Product {

    private java.lang.String name;
    private static int product_count;
    private java.lang.String productID;
    private double unitPrice;

    /**
     * Create a new Product instance.
     * @param pName the name of the product
     * @param pUnitPrice the price of the product
     */
    Product(java.lang.String pName, double pUnitPrice)
    {
        name = pName;
        unitPrice = pUnitPrice;
        product_count++;
        productID = Double.toString(product_count);
    }
    /**
     * Get the product name
     * @return the product name
     */
    public String getName()
    {
        return name;
    }
    /**
     * Set the product name
     * @param newName The new product name
     */
    public void setName(java.lang.String newName)
    {
        name = newName;
    }
    /**
     * Get the product unitPrice
     * @return the unit unitPrice
     */
    public Double getUnitPrice()
    {
        return unitPrice;
    }
    /**
     * Set the unitPrice. If the newUnitPrice < 0 than the unitPrice will not be set.
     * @param newUnitPrice the new unit price
     */
    public void setUnitPrice(java.lang.Double newUnitPrice)
    {
        if (newUnitPrice > 0)
        {
            unitPrice =newUnitPrice;
        }
    }
    /**
     * Get the productID
     * @return the productID
     */
    public String getProductID()
    {
        return productID;
    }
    /**
     * Gets a the unit price in currency format
     * @return The unit price in currency string format
     */
    public String getFormattedUnitPrice()
    {
        return NumberFormat.getCurrencyInstance().format(unitPrice);
    }
    /**
     * A description that includes the product id and unit price in the format
     * @return the sub-description
     */
    public String getSubDescription()
    {
        return "ID: " + productID + " Unit Price: " + unitPrice;
    }
}


