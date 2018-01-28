package com.strobertchs.finalproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.strobertchs.finalproject.R;
import com.strobertchs.finalproject.ServerOrderLay;
import com.strobertchs.finalproject.model.Message;
import com.strobertchs.finalproject.model.Order;
import com.strobertchs.finalproject.utils.ViewUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Amy0127 on 2018-01-22.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private static String TAG = "ServerOrderLay";

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        String [] sa = childText.split(",");

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtProdName = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtProdName.setText(sa[0]);

        TextView txtCount = (TextView) convertView
                .findViewById(R.id.lbllistQuantity);
        txtCount.setText(sa[1]);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final String headerTitle = (String) getGroup(groupPosition);
        final String [] sa = headerTitle.split(",");
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(sa[0]);

        TextView txtPrice = (TextView) convertView
                .findViewById(R.id.lblListPrice);
        txtPrice.setTypeface(null, Typeface.BOLD);
        txtPrice.setText(sa[1]);
        Button orderReady = (Button) convertView.findViewById(R.id.orderReady);
        orderReady.setFocusable(false);
        orderReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification(sa[0]);
            }
        });
        return convertView;
    }

    /**
     * Send order ready notification to User
     * @param orderNum
     */
    private void sendNotification(final String orderNum){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ordersRef = database.getReference("Orders");
        final DatabaseReference tokensRef = database.getReference("UserTokens");
        ordersRef.child(orderNum).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                if(dataSnapshot==null) return;
                Order o = dataSnapshot.getValue(Order.class);
                String email = o.getOrderUser();
                tokensRef.child(ViewUtils.encodeEmailAddress(email)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot ds1) {
                        if(ds1==null) return;
                        String toToken = ds1.getValue().toString();
                        sendNotification(orderNum, toToken);
                        updateOrderStatus(orderNum);
                        reloadView();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Update order status to "NOTIFIED" after sending notification
     * @param orderNum
     */
    private void updateOrderStatus(final String orderNum){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ordersRef = database.getReference("Orders");
        ordersRef.child(orderNum).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Order o = dataSnapshot.getValue(Order.class);
                o.setOrderStatus("NOTIFIED");
                ordersRef.child(orderNum).setValue(o);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Reload ServerOrderLay view.
     */
    private void reloadView(){
        ((ServerOrderLay)_context).prepareListData();
    }


    /**
     * Push a notification message to messages table.
     * @param orderNum String
     * @param toToken String
     */
    private void sendNotification(String orderNum, String toToken){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference msgsRef = database.getReference("messages");
        msgsRef.push().setValue(new Message(FirebaseInstanceId.getInstance().getToken(),
                "Order Ready", "Your order # " + orderNum + " is ready!"));
        Toast.makeText(_context, "Message sent.", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}