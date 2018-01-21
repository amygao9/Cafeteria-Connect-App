package com.strobertchs.finalproject.model;
import java.text.NumberFormat;

public class Product
{
    /**
     * The Product name
     */
    private String name;
    /**
     * Represents the count of Product objects instantiated. Used to set productID
     */
    private static int product_count = 0;
    /**
     * The unique identifier of the product. Based on product_count
     */
    private String productID;
    /**
     * The Product unit price (cost of a single item of the Product)
     */
    private double unitPrice;

    /**
     * The category of the product, the subclass
     */
    private String category;
    /**
     * The product's image ID
     */
    private int imageId;


    /**
     * Create a new Product instance.
     * @param pName
     * @param pUnitPrice
     */
    public Product(String pName, double pUnitPrice, int pImageID)
    {
        name = pName;
        unitPrice = pUnitPrice;
        product_count += 1;
        productID = ""+ product_count;
        imageId = pImageID;
    }

    /**
     * Get the product name
     * @return name - the product's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the product name
     * @param newName - The new product name
     */
    public void setName(String newName)
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
     * Set the product unitPrice
     * @param newUnitPrice - the new unit price
     */
    public void setUnitPrice(Double newUnitPrice)
    {
        unitPrice = newUnitPrice;
    }

    /**
     * Get the product productID
     * @return the productID
     */
    public String getProductID()
    {
        return productID;
    }

    /**
     * Gets a the unit price in currency format $D.DD i.e $9.99.
     * @return The unit price in currency string format
     */
    public String getFormattedUnitPrice()
    {
        return NumberFormat.getCurrencyInstance().format(unitPrice);
    }

    /**
     * Gets the category of the product
     * @return category - the category of the product
     */
    public String getCategory()
    {
        return this.category;
    }

    /**
     * Sets the category of the product
     * @param category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Gets the product's image ID
     * @return imageId - the product's image ID
     */
    public int getImageId()
    {
        return this.imageId;
    }

    /**
     * Sets the product's image ID
     * @param id - the product's new image ID
     */
    public void setImageId(int id)
    {
        this.imageId = id;
    }

    /**
     * A description that includes the product id and unit price in the format i.e "ID: 5 Unit Price: $9.99"
     * @return the sub-description
     */
    public String getSubDescription()
    {
        return "ID: " + productID;
    }
}


