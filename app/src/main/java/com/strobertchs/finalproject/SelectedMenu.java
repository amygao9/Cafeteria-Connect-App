package com.strobertchs.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectedMenu extends AppCompatActivity {

    int selectedMenu;

    public SelectedMenu()
    {

    }

    public SelectedMenu(int selectedMenu)
    {
        this.selectedMenu = selectedMenu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_menu);
    }
}
