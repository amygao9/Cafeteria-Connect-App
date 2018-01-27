package com.strobertchs.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.strobertchs.finalproject.adapter.MainMenuAdapter;
import com.strobertchs.finalproject.model.Cart;
import com.strobertchs.finalproject.model.SavedUsers;
import com.strobertchs.finalproject.utils.Constants;

/**
 * Home page is displayed after student successfully signed in.
 * @author jenny
 */
public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * shopping cart
     */
    public static Cart SHOPPING_CART;

    ListView mainMenuListView;
    MainMenuAdapter mainMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set user on home header
        mainMenuAdapter = new MainMenuAdapter(this, this, Constants.MAIN_MENU_ITEMS, Constants.MAIN_MENU_IMAGE_IDs);
        mainMenuListView = (ListView) findViewById(R.id.menuListView);
        mainMenuListView.setAdapter(mainMenuAdapter);

        //initialize SHOPPING_CART
        initializeShoppingCart();
    }

    private void initializeShoppingCart(){
        if(SHOPPING_CART==null) {
            SHOPPING_CART = new Cart(0.13);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu)
        {
            Intent i = null;
            i = new Intent(this, new Home().getClass());
            startActivity(i);
        }
        else if (id == R.id.nav_cart)
        {
            Intent i = null;
            i = new Intent(this, new CartPage().getClass());
            startActivity(i);
        }
        else if (id == R.id.nav_log_out)
        {
            SavedUsers.deleteCurrentUser();
            Intent i = new Intent(this, new FinalProject().getClass());
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

