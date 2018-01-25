package com.strobertchs.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.strobertchs.finalproject.model.Order;
import com.strobertchs.finalproject.model.SavedUsers;
import com.strobertchs.finalproject.model.CartItem;

public class ServerOrderLay extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView orderTotal;
    Button orderReady;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference order = database.getReference("Orders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_order_lay);

        orderReady = (Button) findViewById(R.id.orderReady);
        orderReady.setFocusable(false);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);


        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        orderReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Order order = dataSnapshot.child(SavedUsers.ORDERNUM).getValue(Order.class);
                listDataHeader.add(SavedUsers.ORDERNUM);
                List<CartItem> currentOrder = Home.SHOPPING_CART.getCartItemList();
                //List<CartItem> currentOrder = order.getCartItems();
                List<String> orderNameList = new ArrayList<>();
                //List<String> orderQuantityList = new ArrayList<>();

                // Adding child data
                for(int i = 0; i < currentOrder.size(); i++) {
                    orderNameList.add(currentOrder.get(i).getProductName());
                    //orderQuantityList.add(Integer.toString(currentOrder.get(i).getQuantity()));
                }

                listDataChild.put(listDataHeader.get(0), orderNameList); // Header, Child data

                //orderTotal.setText(request.getTotal());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Adding child data
        /*listDataHeader.add("User 2940");
        listDataHeader.add("User123");
        listDataHeader.add("User940");

        // Adding child data
        List<String> User2940 = new ArrayList<String>();
        User2940.add("San Pellegrino");
        User2940.add("Rose Pasta");


        List<String> User123 = new ArrayList<String>();
        User123.add("The Conjuring");
        User123.add("Despicable Me 2");


        List<String> User940 = new ArrayList<String>();
        User940.add("2 Guns");
        User940.add("The Smurfs 2");


        listDataChild.put(listDataHeader.get(0), User2940); // Header, Child data
        listDataChild.put(listDataHeader.get(1), User123);
        listDataChild.put(listDataHeader.get(2), User940);*/
    }
}