package com.strobertchs.finalproject.model;


import com.google.firebase.database.Exclude;
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
    @Exclude
    private Date orderTime;

    public Order() {
    }

    public Order(ArrayList<CartItem> cartItems, String orderNumber, String orderUser, Date orderTime) {
        this.cartItems = cartItems;
        this.orderStatus = orderNumber;
        this.orderUser = orderUser;
        this.orderTime = orderTime;
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
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * Set order Date time
     * @param orderTime Date
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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