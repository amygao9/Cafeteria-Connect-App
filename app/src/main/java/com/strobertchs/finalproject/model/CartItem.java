/**
 *
 */
package com.strobertchs.finalproject.model;
import java.text.NumberFormat;

/**
 * Models an item in a shopping cart.
 * @author jenny
 */
public class CartItem
{
    /**
     * The product of the cart item.
     */
    private Product product;
    /**
     * The quantity of the product added to the cart
     */
    private int quantity;

    /**
     * Initializes product to the prod parameter and quantity to the qty parameter
     * @param prod
     * @param qty
     */
    public CartItem(Product prod, int qty)
    {
        product = prod;
        quantity = qty;
    }

    /**
     * Get the quantity of product(s) for the cart item
     * @return the number of items of the product added to the cart.
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Set the quantity of the cart item
     * @param quantity - the number of items of the product added to the cart
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * Get the cost of the cart item, which is the product unit price * quantity
     * @return the cost of the cart item
     */
    public double getPrice()
    {
        return this.product.getUnitPrice() * this.quantity;
    }

    /**
     * Get the name of the product for this cart item
     * @return the name of the product
     */
    public String getProductName()
    {
        return this.product.getName();
    }

    /**
     * Gets a the price of the cart item in currency string format $D.DD i.e $9.99.
     * @return The price in currency string format
     */
    public String getFormattedPrice()
    {
        return NumberFormat.getCurrencyInstance().format(this.getPrice());
    }

    /**
     * Gets the subdescription from the product
     * @return product.getSubDescription() - the subdescription from the product
     */
    public String getSubDescription()
    {
        return this.product.getSubDescription();
    }
}