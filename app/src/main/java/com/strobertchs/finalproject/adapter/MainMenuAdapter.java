package com.strobertchs.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.strobertchs.finalproject.Home;
import com.strobertchs.finalproject.R;
import com.strobertchs.finalproject.SelectedMenu;

/**
 * MainMenuAdapter for rendering ListView menuListView.
 * @Author jenny
 */
public class MainMenuAdapter extends BaseAdapter {
    private Home home;
    String [] menuItemList;
    Context context;
    int [] imageId;

    public MainMenuAdapter(Home home, Home mainActivity, String[] mList, int[] mImageIds) {
        this.home = home;
        menuItemList = mList;
        context = mainActivity;
        imageId = mImageIds;
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
        public void populateHolder(View view){
            menuItemName=(TextView) view.findViewById(R.id.menuItemName);
            menuImage=(ImageView) view.findViewById(R.id.menuImage);
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        }

        holder.populateHolder(convertView);

        holder.menuItemName.setText(menuItemList[position]);
        Picasso.with(context).load(imageId[position]).into(holder.menuImage);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(context, SelectedMenu.class);
                i.putExtra("selectedMainMenu", menuItemList[position]);
                home.startActivity(i);
            }
        });
        return convertView;
    }

}
