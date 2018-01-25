package com.strobertchs.finalproject.model;

import android.content.Context;

import com.strobertchs.finalproject.model.User;

import io.paperdb.Paper;

public class SavedUsers {
    public static User currentUser;

    public static final String FIRSTNAME = "";
    public static final String LASTNAME = "";
    public static final String USER = "";
    public static final String EMAIL = "";

    public static final String ORDERNUM = "1600";

    public static User getCurrentUser(Context context){
        if(currentUser!=null) return currentUser;
        else
        {
            Paper.init(context);
            return new User(Paper.book().read(EMAIL).toString(),
                    Paper.book().read(FIRSTNAME).toString(),
                    Paper.book().read(LASTNAME).toString(), null);
        }
    }

}
