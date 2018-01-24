package com.strobertchs.finalproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.strobertchs.finalproject.Adapters.CartAdapter;

import java.util.ArrayList;
import java.util.Random;

import io.paperdb.Paper;

public class CartPage extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference order;

    TextView txtTotalPrice;
    Button btnPlace;

    ArrayList<Order> cart = new ArrayList<>();

    //CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Paper
        Paper.init(this);

        //Firebase
        database = FirebaseDatabase.getInstance();
        order = database.getReference("Orders");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.total);
        btnPlace = (Button)findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Paper.book().read(SavedUsers.USER);
                int orderNum = randInt();
                Paper.book().write(SavedUsers.ORDERNUM, orderNum);
                Request currentReq = new Request(username, cart);

                order.child(Integer.toString(orderNum)).setValue(currentReq);
                Toast.makeText(CartPage.this, "Order Placed!", Toast.LENGTH_SHORT).show();
                cart.clear(); //empty cart
            }
        });
        loadListFood();

    }
    private void loadListFood() {
        //cart = new Database(this).getCarts();
        //adapter = new CartAdapter(cart.this);
        //recyclerView.setAdapter(adapter);

    }

    private static int randInt() {
        Random randomNum;
        randomNum = new Random();
        int num = randomNum.nextInt(1600) + 1;
        return num;
    }

}

