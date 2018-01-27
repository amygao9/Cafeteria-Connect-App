package com.strobertchs.finalproject.model;

import io.paperdb.Paper;

public class SavedUsers {
    public static User currentUser;

    public static final String FIRSTNAME = "first_name";
    public static final String LASTNAME = "last_name";
    public static final String USERNAME = "user_name";
    public static final String EMAIL = "email";

    public static final String ORDERNUM = "order_num";

    /**
     * This method should be called after Paper is initialized with Paper.init(context)
     * @return User
     */
    public static User getCurrentUser(){
        if(currentUser!=null) return currentUser;
        else
        {
            return new User(Paper.book().read(EMAIL).toString(),
                    Paper.book().read(FIRSTNAME).toString(),
                    Paper.book().read(LASTNAME).toString(), null);
        }
    }

    /**
     * Delete user from Paper.book()
     */
    public static void deleteCurrentUser(){
        currentUser = null;
        Paper.book().delete(SavedUsers.FIRSTNAME);
        Paper.book().delete(SavedUsers.LASTNAME);
        Paper.book().delete(SavedUsers.USERNAME);
        Paper.book().delete(SavedUsers.EMAIL);
    }

    /**
     * Save user in Paper.book()
     * @param user User
     */
    public static void saveCurrentUser(User user){
        currentUser = user;
        Paper.book().write(SavedUsers.FIRSTNAME, user.getFirstName());
        Paper.book().write(SavedUsers.LASTNAME, user.getLastName());
        Paper.book().write(SavedUsers.USERNAME, user.getFullName());
        Paper.book().write(SavedUsers.EMAIL, user.getEmail());
    }
}
