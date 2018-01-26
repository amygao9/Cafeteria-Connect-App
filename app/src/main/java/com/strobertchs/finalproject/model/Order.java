package com.strobertchs.finalproject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

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
    private String orderNumber;
    /**
     * the user who places the order
     */
    private User orderUser;
    /**
     * the time of the order to be placed
     */
    private Date orderTime;

    public Order() {
    }

    public Order(ArrayList<CartItem> cartItems, String orderNumber, User orderUser, Date orderTime) {
        this.cartItems = cartItems;
        this.orderNumber = orderNumber;
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
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Set order number
     * @param orderNumber
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Get user of the order
     * @return User
     */
    public User getOrderUser() {
        return orderUser;
    }

    /**
     * Set user of the order
     * @param orderUser
     */
    public void setOrderUser(User orderUser) {
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
}