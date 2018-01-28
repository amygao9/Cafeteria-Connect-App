package com.strobertchs.finalproject.model;


import com.google.firebase.database.Exclude;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Models an item in a shopping cart.
 * @author jenny, Amy0127
 */

public class Order {
    /**
     * The list containing the collection of CartItem objects.
     */
    private ArrayList<CartItem> cartItems;
    /**
     * The order number
     */
    private String orderStatus;
    /**
     * the user who places the order
     */
    private String orderUser;
    /**
     * the time of the order to be placed
     */

    private String orderDateTime;
    /**
     * The list containing the collection of CartItem objects.
     */
    private double taxRate;


    public Order() {
    }


    public Order(ArrayList<CartItem> cartItems, String orderNumber, String orderUser, String orderTime, double tRate) {

        this.cartItems = cartItems;
        this.orderStatus = orderNumber;
        this.orderUser = orderUser;
        this.orderDateTime = orderTime;
        this.taxRate = tRate;
    }

    /**
     * Get array list of cart items in this order
     * @return ArrayList of CartItem
     */
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Set CartItems
     * @param cartItems - ArrayList of CartItem
     */
    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Get order number
     * @return order number
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Set order number
     * @param orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Get user of the order
     * @return User
     */
    public String getOrderUser() {
        return orderUser;
    }

    /**
     * Set user of the order
     * @param orderUser String
     */
    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    /**
     * get order date time
     * @return Date
     */
    public String getOrderDateTime() {
        return orderDateTime;
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
     * Get sub total
     * @return
     */
    @Exclude
    private double getSubtotal()
    {
        double total = 0.0;
        for (int i = 0; i < cartItems.size(); i ++)
        {
            CartItem cartItem = cartItems.get(i);
            total += cartItem.getPrice();
        }
        return total;
    }

    /**
     * Get formatted sub total price
     * @return String
     */
    @Exclude
    public String getFormattedSubTotal(){
        return NumberFormat.getCurrencyInstance().format(this.getSubtotal());
    }

    /**
     * Get formatted total tax
     * @return String
     */
    @Exclude
    public String getFormattedTotalTax(){
        double totalTax = this.getSubtotal() * taxRate;
        return NumberFormat.getCurrencyInstance().format(totalTax);
    }

    /**
     * Get formatted total price
     * @return String
     */
    @Exclude
    public String getFormattedTotalPrice(){

        return NumberFormat.getCurrencyInstance().format(this.getSubtotal() * (1 + taxRate));
    }

    /**
     * Set order Date time
     * @param orderDateTime Date
     */
    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    /**
     * Get product name and count list of this order
     * @return List of product names
     */
    @Exclude
    public List<String> getProductNameCountList(){
        List<String> prodNameCountList = new ArrayList<>();
        for(int i = 0; i < this.cartItems.size(); i++){
            CartItem ci = cartItems.get(i);
            prodNameCountList.add(ci.getProductName()+","+ci.getQuantity());
        }
        return prodNameCountList;
    }

    /**
     * Get product name list of this order
     * @return List of product names
     */
    @Exclude
    public List<String> getProductNameList(){
        List<String> prodNameList = new ArrayList<>();
        for(int i = 0; i < this.cartItems.size(); i++){
            prodNameList.add(cartItems.get(i).getProductName());
        }
        return prodNameList;
    }
}