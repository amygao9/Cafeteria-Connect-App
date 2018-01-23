package com.strobertchs.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.strobertchs.finalproject.Adapters.ExpandableListAdapter;
import com.strobertchs.finalproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerOrderLay extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_order_lay);

        listView = (ExpandableListView)findViewById(R.id.listExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

    }
    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("User 12526");

        List<String> user12526 = new ArrayList<>();
        user12526.add("expand food items");
    }
}
