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

import com.strobertchs.finalproject.adapter.ProductAdapter;
import com.strobertchs.finalproject.model.Drinks;
import com.strobertchs.finalproject.model.Food;
import com.strobertchs.finalproject.model.Product;
import com.strobertchs.finalproject.model.SavedUsers;

import java.util.ArrayList;
import java.util.List;

/**
 * SelectedMenu page is displayed after user clicks on a main menu in the Home page.
 * @author jenny
 */
public class SelectedMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String selectedMainMenu;
    private ProductAdapter mProdAdapter;
    ListView selectedMenuListView;


    public SelectedMenu() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_menu);
        this.selectedMainMenu = getIntent().getStringExtra("selectedMainMenu");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(selectedMainMenu);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initialize the list of products and instantiate ProductAdapter
        List<Product> pList = populateProductList();
        mProdAdapter = new ProductAdapter(this, this, pList);

        //Populate ListView selectedMenuList
        selectedMenuListView = (ListView)findViewById(R.id.selectedMenuList);
        selectedMenuListView.setAdapter(mProdAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public List<Product> populateProductList() {
        List<Product> pList = new ArrayList<Product>();
        if (selectedMainMenu.equals("Breakfast")) {
            pList.add(new Food("Hard Boiled Egg", 1.00, R.drawable.hard_boiled_egg, "Egg"));
            pList.add(new Food("Scrambled Eggs", 2.00, R.drawable.scrambled_eggs, "Egg, milk"));
            pList.add(new Food("Sausage", 2.00, R.drawable.sausage, "Sausage"));
            pList.add(new Food("Bacon", 1.50, R.drawable.bacon, "Bacon, oil"));
            pList.add(new Food("Breakfast Wrap", 3.22, R.drawable.breakfast_wrap, "Cheese, flour, eggs, lettuce"));
        }
        else if (selectedMainMenu.equals("Lunch")) {
            pList.add(new Food("Pepperoni Pizza", 4.25, R.drawable.pepperoni_pizza, "Cheese, flour, pepperoni"));
            pList.add(new Food("Cheese Pizza", 4.25, R.drawable.cheese_pizza, "Flour, pepperoni"));
            pList.add(new Food("Pasta", 4.50, R.drawable.pasta, "Flour, milk, tomato"));
            pList.add(new Food("Oven Roast Chicken", 4.50, R.drawable.oven_roasted_chicken, "Cheese, flour, pepperoni"));
            pList.add(new Food("Steamed Vegetables", 3.50, R.drawable.steamed_vegetables, "Cheese, flour, pepperoni"));
            pList.add(new Food("Potato Wedges", 4.00, R.drawable.potato_wedges, "Chicken, potato, pasta, tomato"));
            pList.add(new Food("Fries", 4.00, R.drawable.fries, "Chicken, potato, pasta, tomato"));
            pList.add(new Food("Nuggets", 4.15, R.drawable.nuggets, "Chicken, potato, pasta, tomato"));
            pList.add(new Food("Fried Rice", 3.75, R.drawable.fried_rice, "Rice, tomato, cheese, flour, pepperoni"));
            pList.add(new Food("Burger", 5.10, R.drawable.burger, "Cheese, flour, beef, lettuce"));
            pList.add(new Food("Perogies", 4.75, R.drawable.perogies, "Cheese, flour"));
            pList.add(new Food("Soup", 4.00, R.drawable.soup, "Chicken, potato, pasta, tomato"));

        }
        else if (selectedMainMenu.equals("Desserts")) {
            pList.add(new Food("Chocolate Cookie", 1.25, R.drawable.chocolate_cookie, "Chocolate chips, flour, milk"));
            pList.add(new Food("Double Chocolate Cookie", 1.25, R.drawable.double_chocolate_cookies, "Chocolate chips, cocoa, milk"));
            pList.add(new Food("Oatmeal Cookie", 1.50, R.drawable.oatmeal_cookie, "Chocolate chips, oatmeal, milk"));
            pList.add(new Food("Brownie", 1.50, R.drawable.brownies, "Cocoa, milk, flour"));
            pList.add(new Food("Jelly", 1.50, R.drawable.jelly, "Gelatin, flavouring"));
            pList.add(new Food("Yogurt", 1.50, R.drawable.yogurt, "Milk"));
        }
        else if (selectedMainMenu.equals("Drinks")) {
            pList.add(new Drinks("Coffee", 1.25, R.drawable.coffee, "Hot Drink"));
            pList.add(new Drinks("Tea", 1.25, R.drawable.tea, "Hot Drink"));
            pList.add(new Drinks("Hot Water", 1.25, R.drawable.water, "Hot Drink"));
            pList.add(new Drinks("Sanpellegrino", 1.25, R.drawable.sanpellegrino, "Cold, fizzy drink"));
        }
        return pList;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            Intent i = null;
            i = new Intent(this, new Home().getClass());
            startActivity(i);
        }
        else if (id == R.id.nav_cart) {
            Intent i = null;
            i = new Intent(this, new CartPage().getClass());
            startActivity(i);
        }
//        else if (id == R.id.nav_orders) {
//
//        }
        else if (id == R.id.nav_log_out) {
            SavedUsers.deleteCurrentUser();
            Intent i = new Intent(this, new FinalProject().getClass());
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
