package com.strobertchs.finalproject;

public class CartItem {
    private Product product;
    private int quantity;

    /**
     * Initializes product to the prod parameter and quantity to the qty parameter
     * @param prod an instance of a Product to set the product of the cart item
     * @param qty the number of products
     */
    CartItem(Product prod, int qty)
    {
        product = prod;
        quantity = qty;
    }

    /**
     *
     * @return the formatted unit price
     */
    public String getFormattedPrice()
    {
        return product.getFormattedUnitPrice();
    }
    /**
     * Get the cost of the cart item, which is the product unit price * quantity
     * @return the cost of the cart item
     */
    public double getPrice()
    {
        return product.getUnitPrice() * quantity;
    }
    /**
     * Set the quantity of the cart item
     * @param quant the number of items of the product added to the cart
     */
    public void setQuantity (int quant)
    {
        quantity = quant;
    }
    /**
     * Get the quantity of product(s) for the cart item
     * @return the number of items of the product added to the cart.
     */
    public int getQuantity ()
    {
        return quantity;
    }
    /**
     * Get the name of the product for this cart item
     * @return the name of the product
     */
    public String getProductName()
    {
        return product.getName();
    }
    /**
     * Gets the subdescription from the product
     * @return the subdescription from the product
     */
    public String getSubDescription()
    {
        return product.getSubDescription();
    }

}



