package com.strobertchs.finalproject;

import android.app.Activity;
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

    String selectedMenus;
    private ProductAdapter mProdAdapter;
    ListView listProduct;


    public SelectedMenu() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_menu);
        this.selectedMenus = getIntent().getStringExtra("selectedMenu");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(selectedMenus);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        List<Product> pList = populateProductList(); //initialize the list of products
        mProdAdapter = new ProductAdapter(this, pList);

        listProduct = (ListView)findViewById(R.id.selectedMenuList);
        listProduct.setAdapter(mProdAdapter);
        mProdAdapter.notifyDataSetChanged();
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
        if (selectedMenus.equals("Breakfast")) {
            pList.add(new Food("Hard Boiled Egg", 1.00, R.drawable.breakfast, "Egg"));
            pList.add(new Food("Sausage", 2.00, R.drawable.breakfast, "Sausage"));
            pList.add(new Food("Becken", 3.00, R.drawable.breakfast, "Becken"));
            pList.add(new Food("Noodles", 5.00, R.drawable.breakfast, "Noddles"));
        }
        else if (selectedMenus.equals("Lunch")) {
            pList.add(new Food("Pepperoni Pizza", 85.99999, R.drawable.lunch, "Cheese, flour, pepperoni"));
        }
        else if (selectedMenus.equals("Desserts")) {
            pList.add(new Food("Chocolate Chip Cookie", 1.25, R.drawable.desserts, "Chocolate chips"));
        }
        else if (selectedMenus.equals("Drinks")) {
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
/*            i = new Intent(this, new CartPage().getClass());
            startActivity(i);
  */      }
        else if (id == R.id.nav_orders) {

        }
        else if (id == R.id.nav_log_out) {
/*            SavedUsers.currentUser = null;
            Intent i = new Intent(this, new FinalProject().getClass());
            startActivity(i);
 */       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class ProductAdapter extends BaseAdapter {

        Context context;
        List<Product> productList;

        public ProductAdapter(SelectedMenu pActivity, List<Product> pList) {
            context = pActivity;
            productList = pList;
        }

        @Override
        public int getCount() {
            return productList.size();
        }

        @Override
        public Object getItem(int whichItem) {
            return productList.get(whichItem);
        }

        @Override
        public long getItemId(int whichItem) {
            return whichItem;
        }

/*        public void addProduct(Product prod) {
            productList.add(prod);
            notifyDataSetChanged();
        }
*/
        public class Holder {
            TextView txtName;
            TextView txtPrice;
            ImageView prodImage;
        }

        @Override
        public View getView(final int whichItem, View convertView, ViewGroup viewGroup) {
            Holder holder = new Holder();
            final View view;

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.product, null);

            holder.txtName = (TextView) view.findViewById(R.id.name);
            holder.txtPrice = (TextView) view.findViewById(R.id.unitPrice);
            holder.prodImage = (ImageView) view.findViewById(R.id.imageId);

            Product tempProduct = productList.get(whichItem);
            holder.txtName.setText(tempProduct.getName());
            holder.txtPrice.setText(tempProduct.getFormattedUnitPrice());
            Picasso.with(context).load(tempProduct.getImageId()).into(holder.prodImage);

            // make the addToCart a clickable action
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(), productList.get(whichItem).getName() + " added to cart", Toast.LENGTH_SHORT).show();
                    //Home.shoppingCart.addCartItem(new CartItem(productList.get(whichItem), 1));
                    DialogProduct dialog = new DialogProduct();
                    dialog.sendProduct(productList.get(whichItem));
                    dialog.setContext(context);
                    // Create the dialog
                    dialog.show(getFragmentManager(), "123");
                }
            });


            return view;
        }


    }

}
