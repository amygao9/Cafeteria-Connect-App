package com.strobertchs.finalproject;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Cart {
    private double taxRate;
    private ArrayList<CartItem> cartItems;
    /**
     * Creates an instance of a Cart. Initializes the taxRate via the parameter and initializes cartItems as an empty ArrayList of CartItem objects
     * @param tRate - the cart tax rate in percentage form i.e 0.13 for 13%
     */
    public Cart(double tRate)
    {
        taxRate = tRate;
        cartItems = new ArrayList<CartItem>();
    }
    /**
     * Set the taxRate of the Cart
     * @param tRate - - the new taxRate in percentage form i.e 0.13 for 13%
     */
    public void setTaxRate(double tRate)
    {
        taxRate = tRate;
    }
    /**
     * Get the taxRate
     * @return the Cart taxRate
     */
    public double getTaxRate()
    {
        return taxRate;
    }
    /**
     * Add a new CartItem to the Cart.
     * @param cItem - The new CartItem to be added to the list of CartItem objects
     */
    public void addCartItem(CartItem cItem)
    {
        cartItems.add(cItem);
    }
    /**
     * Get the number of CartItems in the Cart
     * @return the number of CartItems in the Cart
     */
    public int getSize()
    {
        return cartItems.size();
    }
    /**
     * Get the total of all items in the Cart, excluding tax
     * @return the subtotal of all items in the cart
     */
    public double getSubtotal()
    {
        double subTotal = 0;

        for(int i = 0; i < cartItems.size() ; i++) {
            subTotal += cartItems.get(i).getPrice();
        }
        return subTotal;
    }
    /**
     * Get the total of all items in the Cart in string currency format, excluding tax
     * @return currency formatted string of the cart subtotal
     */
    public String getFormattedSubtotal()
    {
        return NumberFormat.getCurrencyInstance().format(getSubtotal());
    }
    /**
     * Get the amount of tax applied to the cart
     * @return The tax amount applied to the cart
     */
    public double getTotalTax()
    {
        return getSubtotal() * taxRate;
    }
    /**
     * Get the amount of tax applied to the cart in string currency format
     * @return currency formatted string of the tax amount
     */
    public String getFormattedTotalTax()
    {
        return NumberFormat.getCurrencyInstance().format(getTotalTax());
    }
    /**
     * Get the total of all items in the Cart, including tax
     * @return the total of all items in the cart
     */
    public double getTotal()
    {
        return getTotalTax() + getSubtotal();
    }
    /**
     * Get the total of all items int the cart in string currency format
     * @return currency formatted string of the total
     */
    public String getFormattedTotal()
    {
        return NumberFormat.getCurrencyInstance().format(getTotal());
    }
    /**
     * Get the ArrayList of the Cart's collection of cart items.
     * @return the Cart's ArrayList of cart items.
     */
    public java.util.ArrayList<CartItem> getCartItemList() {
        return cartItems;
    }
    /**
     * Textual printout of the Cart to the screen
     */
    public void printCart() {
        System.out.println("YOUR CART");
        System.out.println(" ");
        for (int i=0; i < cartItems.size(); i++)
        {
            System.out.println(cartItems.get(i).getProductName());
            System.out.println("Unit Price: "+ cartItems.get(i).getFormattedPrice() + " Qty: " + cartItems.get(i).getQuantity() + " Cost: $"+ cartItems.get(i).getPrice());
            System.out.println(" ");
        }

        System.out.println("Subtotal: " + getFormattedSubtotal());
        System.out.println("Tax (13%): " + getFormattedTotalTax());
        System.out.println("Total: " + getFormattedTotal());

    }
}