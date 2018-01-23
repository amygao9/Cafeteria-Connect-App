/**
 *
 */
package com.strobertchs.finalproject.model;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Models a shopping cart
 * @author jenny
 */
public class Cart
{
    /**
     * The list containing the collection of CartItem objects.
     */
    private ArrayList<CartItem> cartItems;
    /**
     * The list containing the collection of CartItem objects.
     */
    private double taxRate;

    /**
     * Add a new CartItem to the CartPage
     * @param tRate - the cart tax rate in percentage form i.e 0.13 for 13%
     */
    public Cart(double tRate)
    {
        taxRate = tRate;
        cartItems = new ArrayList<CartItem>();
    }

    /**
     * Set the taxRate of the CartPage
     * @param taxRate
     */
    public void setTaxRate(double taxRate)
    {
        this.taxRate = taxRate;
    }

    /**
     * Get the taxRate
     * @return taxRate - the CartPage taxRate
     */
    public double getTaxRate()
    {
        return this.taxRate;
    }

    /**
     * Add a new CartItem to the CartPage.
     * @param cItem - The new CartItem to be added to the list of CartItem objects
     */
    public void addCartItem(CartItem cItem)
    {
        boolean itemExist = false;
        for (int i = 0; i < getSize(); i ++)
        {
            CartItem cartItem = cartItems.get(i);
            if (cartItem.getProductName().equals(cItem.getProductName()))
            {
                int totalQuantity = cartItem.getQuantity() + cItem.getQuantity();
                cartItem.setQuantity(totalQuantity);
                itemExist = true;
                break;
            }
        }
        if (itemExist == false)
        {
            cartItems.add(cItem);
        }
    }

    /**
     * Find the cartItem by product name
     * @param pName - name of the product in the cartItem
     * @return cartItem
     */
    public CartItem findCartItem(String pName)
    {
        CartItem cartItem = null;
        for (int i = 0; i < getSize(); i ++)
        {
            cartItem = cartItems.get(i);
            if (cartItem.getProductName().equals(pName))
            {
                break;
            }
        }
        return cartItem;
    }

    /**
     * Deletes cartItme
     * @param dItem - the item that should be deleted
     */
    public void deleteCartItem(CartItem dItem) {
        for (int i = 0; i < getSize(); i++) {
            CartItem cartItem = cartItems.get(i);
            if (cartItem.getProductName().equals(dItem.getProductName())) {
                int totalQuantity = cartItem.getQuantity() - dItem.getQuantity();
                if(totalQuantity==0){
                    this.cartItems.remove(i);
                }else {
                    cartItem.setQuantity(totalQuantity);
                }
                break;
            }
        }
    }

    /**
     * Get the number of CartItems in the CartPage
     * @return cartItems.size() - the number of CartItems in the CartPage
     */
    public int getSize()
    {
        return cartItems.size();
    }

    /**
     * Get the total of all items in the CartPage, excluding tax
     * @return total - the subtotal of all items in the cart
     */
    public double getSubtotal()
    {
        double total = 0.0;
        for (int i = 0; i < getSize(); i ++)
        {
            CartItem cartItem = cartItems.get(i);
            total += cartItem.getPrice();
        }
        return total;
    }

    /**
     * Get the total of all items in the CartPage in string currency format, excluding tax
     * @return currency formatted string of the subtotal amount
     */
    public String getFormattedSubtotal()
    {
        return NumberFormat.getCurrencyInstance().format(this.getSubtotal());
    }

    /**
     * Get the amount of tax applied to the cart
     * @return totalTax - The tax amount applied to the cart
     */
    public double getTotalTax()
    {
        double totalTax = this.getSubtotal() * taxRate;
        return totalTax;
    }

    /**
     * Get the total of all items in the CartPage in string currency format, excluding tax
     * @return currency formatted string of the tax amount
     */
    public String getFormattedTotalTax()
    {
        return NumberFormat.getCurrencyInstance().format(this.getTotalTax());
    }

    /**
     * Get the total of all items in the CartPage, including tax
     * @return this.getSubtotal() + this.getTotalTax() - the total of all items in the cart
     */
    public double getTotal()
    {
        return this.getSubtotal() + this.getTotalTax();
    }

    /**
     * Get the total of all items in the cart in string currency format
     * @return currency formatted string of the total
     */
    public String getFormattedTotal()
    {
        return NumberFormat.getCurrencyInstance().format(this.getTotal());
    }

    /**
     * Get the ArrayList of the CartPage's collection of cart items.
     * @return cartItems - the CartPage's ArrayList of cart items.
     */
    public ArrayList<CartItem> getCartItemList()
    {
        return cartItems;
    }

    /**
     * Textual printout (to the console, not the App) of the CartPage to the screen that looks like the following example:
     *      YOUR CART
     *
     *      Product Name1
     *      Unit Price: $9.99  Qty: 1   Cost: $9.99
     *
     *      Product Name2
     *      Unit Price: $12.99  Qty: 2  Cost: $25.98
     *
     *      Subtotal: $35.97
     *      Tax (13%): $4.68
     *      Total:  $40.65
     */
    public void printCart()
    {
        System.out.println("YOUR CART \n");
        for (int i = 0; i < getSize(); i ++)
        {
            CartItem cartItem = cartItems.get(i);
            System.out.println(cartItem.getProductName());
            String subDesc = cartItem.getSubDescription();
            System.out.println(subDesc.substring(subDesc.indexOf("Unit Price")) + "  Qty: " + cartItem.getQuantity() + "   Cost: " + cartItem.getFormattedPrice() + "\n");
        }
        System.out.println("Subtotal: " + this.getFormattedSubtotal());
        System.out.println("Tax (13%): " + this.getFormattedTotalTax());
        System.out.println("Total: " + this.getFormattedTotal());
    }
}