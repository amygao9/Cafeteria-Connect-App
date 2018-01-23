package com.strobertchs.finalproject;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private String username;
    private List<Order> cartList;

    public Request(String username, List<Order> cartList) {
        this.username = username;
        this.cartList = cartList;
    }

    public List<Order> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<Order> cartList) {
        this.cartList = cartList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
