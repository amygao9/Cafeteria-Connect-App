package com.strobertchs.finalproject.utils;

import com.strobertchs.finalproject.R;

/**
 * Constants for this project.
 * @author jenny
 */

public class Constants {
    public static String[] MAIN_MENU_ITEMS = new String[] {
            "Breakfast",
            "Lunch",
            "Desserts",
            "Drinks"
    };

    public static int [] MAIN_MENU_IMAGE_IDs = {
            R.drawable.breakfast,
            R.drawable.lunch,
            R.drawable.desserts,
            R.drawable.drinks
    };
    public static String VALID_EMAIL_SERVER = "@ycdsbk12.ca";
    public static String CAFETERIA_STAFF_PASSWORD = "0000";
    public static String ORDER_OPENED = "OPENED";
    public static String ORDER_CLOSED = "CLOSED";
    public static String FIREBASE_MESSAGING_URL="https://fcm.googleapis.com/v1/projects/";
    public static String PROJECT_URL = "cafeteria-app-final/messages:send";
}
