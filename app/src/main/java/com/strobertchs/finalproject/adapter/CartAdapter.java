package com.strobertchs.finalproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.strobertchs.finalproject.CartPage;
import com.strobertchs.finalproject.R;
import com.strobertchs.finalproject.model.CartItem;

import java.util.ArrayList;

/**
 * Created by jenny on 2018-01-26.
 */
public class CartAdapter extends ArrayAdapter<CartItem> {

    public CartAdapter(CartPage context, ArrayList<CartItem> cartItemList) {
        super(context, 0, cartItemList);
    }

    public class Holder {
        TextView txtName;
        TextView txtPrice;
        TextView txtQuantity;
        ImageView prodImage;

        public void populateHolder(View view) {
            txtName = (TextView) view.findViewById(R.id.name);
            txtPrice = (TextView) view.findViewById(R.id.itemTotalPrice);
            txtQuantity = (TextView) view.findViewById(R.id.itemQuantity);
            prodImage = (ImageView) view.findViewById(R.id.imageId);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Holder holder = new Holder();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, viewGroup, false);
        }
        holder.populateHolder(convertView);

        CartItem cartItem = getItem(position);
        holder.txtName.setText(cartItem.getProductName());
        holder.txtQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.txtPrice.setText(cartItem.getFormattedPrice());
        Picasso.with(getContext()).load(cartItem.getProductImageId()).into(holder.prodImage);

        return convertView;
    }


}
