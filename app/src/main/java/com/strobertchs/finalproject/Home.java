package com.strobertchs.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;
import com.strobertchs.finalproject.model.Cart;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //TextView fullName;

    ListView menuListView;
    public static Cart shoppingCart;

    MainMenuAdapter list_adapter;
    String[] menuItems = new String[] { "Breakfast",
            "Lunch",
            "Desserts",
            "Drinks"
    };

    private static int [] menuItemImages={R.drawable.breakfast,
            R.drawable.lunch,
            R.drawable.desserts,
            R.drawable.drinks};

    //Initiate Firebase
    //final FirebaseDatabase database = FirebaseDatabase.getInstance();
    //final DatabaseReference menuCategory = database.getReference("Category");

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
        //View header = navigationView.getHeaderView(0);
        //fullName = (TextView)findViewById(R.id.fullName);
        //fullName.setText(SavedUsers.currentUser.getFullName());

        list_adapter = new MainMenuAdapter(this, menuItems, menuItemImages);
        menuListView = (ListView) findViewById(R.id.menuListView);
        menuListView.setAdapter(list_adapter);

        shoppingCart = new Cart(0.13);

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
        else if (id == R.id.nav_orders)
        {
        }
        else if (id == R.id.nav_log_out)
        {
            SavedUsers.currentUser = null;
            Intent i = new Intent(this, new FinalProject().getClass());
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class MainMenuAdapter extends BaseAdapter{
        String [] menuItemList;
        Context context;
        int [] imageId;
        private LayoutInflater inflater=null;

        public MainMenuAdapter(Home mainActivity, String[] mList, int[] mImageIds) {
            menuItemList=mList;
            context=mainActivity;
            imageId=mImageIds;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return menuItemList.length;
        }

        @Override
        public Object getItem(int position) {
            return menuItemList[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder
        {
            TextView menuItemName;
            ImageView menuImage;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder=new Holder();
            final View view;
            view = inflater.inflate(R.layout.menu_item, null);

            holder.menuItemName=(TextView) view.findViewById(R.id.menuItemName);
            holder.menuImage=(ImageView) view.findViewById(R.id.menuImage);

            holder.menuItemName.setText(menuItemList[position]);
            Picasso.with(context).load(imageId[position]).into(holder.menuImage);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = null;
                    i = new Intent(context, SelectedMenu.class);
                    i.putExtra("selectedMenu", menuItemList[position]);
                    startActivity(i);
                }
            });
            return view;
        }

    }

}

