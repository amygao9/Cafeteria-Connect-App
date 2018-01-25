package com.strobertchs.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
        mProdAdapter = new ProductAdapter(this, pList);

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
            pList.add(new Food("Hard Boiled Egg", 1.00, R.drawable.breakfast, "Egg"));
            pList.add(new Food("Sausage", 2.00, R.drawable.breakfast, "Sausage"));
            pList.add(new Food("Becken", 3.00, R.drawable.breakfast, "Becken"));
            pList.add(new Food("Noodles", 5.00, R.drawable.breakfast, "Noddles"));
        }
        else if (selectedMainMenu.equals("Lunch")) {
            pList.add(new Food("Pepperoni Pizza", 85.99999, R.drawable.lunch, "Cheese, flour, pepperoni"));
        }
        else if (selectedMainMenu.equals("Desserts")) {
            pList.add(new Food("Chocolate Chip Cookie", 1.25, R.drawable.desserts, "Chocolate chips"));
        }
        else if (selectedMainMenu.equals("Drinks")) {
            pList.add(new Drinks("Coffee", 1.25, R.drawable.drinks, "Hot Drink"));
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
        else if (id == R.id.nav_orders) {

        }
        else if (id == R.id.nav_log_out) {
            SavedUsers.currentUser = null;
            Intent i = new Intent(this, new FinalProject().getClass());
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * ProductAdapter for rendering SelectedMenuList view.
     */
    public class ProductAdapter extends ArrayAdapter<Product> {

        public ProductAdapter(SelectedMenu pActivity, List<Product> pList) {
            super(pActivity, 0, pList);
        }

        public class Holder {
            TextView txtName;
            TextView txtPrice;
            ImageView prodImage;
            public void populateHolder(View view) {
                txtName = (TextView) view.findViewById(R.id.name);
                txtPrice = (TextView) view.findViewById(R.id.unitPrice);
                prodImage = (ImageView) view.findViewById(R.id.imageId);
            }
        }

        @Override
        public View getView(final int whichItem, View convertView, ViewGroup viewGroup) {
            Holder holder = new Holder();

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.product, viewGroup, false);
            }
            holder.populateHolder(convertView);

            Product tempProduct = getItem(whichItem);
            holder.txtName.setText(tempProduct.getName());
            holder.txtPrice.setText(tempProduct.getFormattedUnitPrice());
            Picasso.with(getContext()).load(tempProduct.getImageId()).into(holder.prodImage);

            // make the addToCart a clickable action
            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Create the dialog for showing prouct details
                    DialogProduct dialog = new DialogProduct();
                    dialog.sendProduct(getItem(whichItem));
                    dialog.setContext(getContext());
                    dialog.show(getFragmentManager(), "123");
                }
            });

            return convertView;
        }


    }

}
