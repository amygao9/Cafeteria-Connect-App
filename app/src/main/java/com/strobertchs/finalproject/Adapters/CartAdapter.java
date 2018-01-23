package com.strobertchs.finalproject.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.strobertchs.finalproject.Order;
import com.strobertchs.finalproject.model.Product;
import com.strobertchs.finalproject.R;

/**
 * Created by Amy0127 on 2018-01-21.
 */
class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public interface OnItemClickListener {
        void onItemClick(Product item);
    }
    public TextView txt_cart_name,txt_price;
    private OnItemClickListener itemClickListener;

    private ArrayList<Product> listData = new ArrayList<>();
    private Context context;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_cart_name = (TextView)itemView.findViewById(R.id.cart_item_name);
        txt_price = (TextView)itemView.findViewById(R.id.cart_item_Price);
    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private ArrayList <Order> listData = new ArrayList<Order>();
    private Context context;
    private int position;


    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        //TextDrawable drawable = TextDrawable.builder()
         //       .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        //holder.img_cart_count.setImageDrawable(drawable);

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
            holder.txt_price.setText(fmt.format(price));
    }

    @Override
    public int getItemCount() {

        return listData.size();
    }


    //@Override
    //public void onClick(View view) {

    //}
}