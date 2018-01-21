package com.strobertchs.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.strobertchs.finalproject.model.CartItem;
import com.strobertchs.finalproject.model.Drinks;
import com.strobertchs.finalproject.model.Food;
import com.strobertchs.finalproject.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SelectedMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String selectedMenu;
    private ProductAdapter mProdAdapter;


    public SelectedMenu() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_menu);
        this.selectedMenu = getIntent().getStringExtra("selectedMenu");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(selectedMenu);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mProdAdapter = new ProductAdapter(this);
        ListView listProduct = (ListView) findViewById(R.id.selectedMenuList);
        listProduct.setAdapter(mProdAdapter);
        populateProductList(); //initialize the list of products
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

    public void populateProductList() {
        if (selectedMenu.equals("Breakfast")) {
            mProdAdapter.addProduct(new Food("Hard Boiled Egg", 1.00, R.drawable.breakfast, "Egg"));
        }
        else if (selectedMenu.equals("Lunch")) {
            mProdAdapter.addProduct(new Food("Pepperoni Pizza", 85.99999, R.drawable.lunch, "Cheese, flour, pepperoni"));
        }
        else if (selectedMenu.equals("Desserts")) {
            mProdAdapter.addProduct(new Food("Chocolate Chip Cookie", 1.25, R.drawable.desserts, "Chocolate chips"));
        }
        else if (selectedMenu.equals("Drinks")) {
            mProdAdapter.addProduct(new Drinks("Coffee", 1.25, R.drawable.drinks, "Hot Drink"));
        }
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
        else if (id == R.id.nav_orders) {
        }
        else if (id == R.id.nav_log_out) {
            savedUser.currentUser = null;
            Intent i = new Intent(this, new FinalProject().getClass());
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class ProductAdapter extends BaseAdapter {

        Context context;
        List<Product> productList = new ArrayList<Product>();

        public ProductAdapter(SelectedMenu mainActivity) {
            context = mainActivity;
        }


        public int getCount() {
            return productList.size();
        }

        public Product getItem(int whichItem) {
            return productList.get(whichItem);
        }

        public long getItemId(int whichItem) {
            return whichItem;
        }

        public void addProduct(Product prod) {
            productList.add(prod);
            notifyDataSetChanged();
        }

        public class Holder {
            TextView txtName;
            TextView txtPrice;
            ImageView prodImage;
        }

        @Override
        public View getView(final int whichItem, View view, ViewGroup viewGroup) {
            Holder holder = new Holder();
            // Implement this method next

            // Has view been inflated already
            if (view == null) {

                // If not, do so here
                // First create a LayoutInflater
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                // Now instantiate view using inflater.inflate
                // using the listitem layout
                view = inflater.inflate(R.layout.product, viewGroup, false);
                // The false parameter is necessary
                // because of the way that we want to use productitem

            }// End if

            holder.txtName = (TextView) view.findViewById(R.id.name);
            holder.txtPrice = (TextView) view.findViewById(R.id.unitPrice);
            holder.prodImage = (ImageView) view.findViewById(R.id.imageId);

            // make the addToCart a clickable action
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), productList.get(whichItem).getName() + " added to cart", Toast.LENGTH_SHORT).show();
                    Home.shoppingCart.addCartItem(new CartItem(productList.get(whichItem), 1));
                }
            });

            Product tempProduct = productList.get(whichItem);
            holder.txtName.setText(tempProduct.getName());
            holder.txtPrice.setText(tempProduct.getFormattedUnitPrice());
            Picasso.with(context).load(tempProduct.getImageId()).into(holder.prodImage);


            return view;
        }


    }

}
