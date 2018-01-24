package com.strobertchs.finalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.strobertchs.finalproject.model.CartItem;
import com.strobertchs.finalproject.model.Product;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by jenny on 2018-01-21.
 */


public class DialogProduct extends DialogFragment{

    private Product dProduct;
    private CartItem dCartItem;
    private Context mContext;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_product, null);




        TextView btnContinue = (TextView) dialogView.findViewById(R.id.txtContinue);
        TextView btnCheckout = (TextView) dialogView.findViewById(R.id.txtCheckout);
        ImageButton btnAdd = (ImageButton) dialogView.findViewById(R.id.buttonAdd);
        ImageButton btnDelete = (ImageButton) dialogView.findViewById(R.id.buttonMinus);
        TextView prodName = (TextView) dialogView.findViewById(R.id.prodName);
        TextView subdescription = (TextView) dialogView.findViewById(R.id.subdescription);
        TextView cartItemPrice = (TextView) dialogView.findViewById(R.id.unitPrice);
        final TextView quantity = (TextView) dialogView.findViewById(R.id.quantity);
        TextView prodPrice = (TextView) dialogView.findViewById(R.id.prodPrice);

        TextView txtPrice = (TextView) dialogView.findViewById(R.id.textView3);
        TextView txtQuantity = (TextView) dialogView.findViewById(R.id.textView8);

        txtPrice.setText("Price");
        txtQuantity.setText("Quantity");

        //Find the cartItem for this product in shopping cart
        dCartItem = Home.shoppingCart.findCartItem(this.dProduct.getName());
        if(dCartItem==null) dCartItem = new CartItem(this.dProduct, 0);
        String tPrice = dCartItem.getFormattedPrice();
        int tQuantity = dCartItem.getQuantity();

        prodName.setText(dProduct.getName());
        subdescription.setText(dProduct.getSubDescription());
        cartItemPrice.setText(tPrice);
        quantity.setText(String.valueOf(tQuantity));
        prodPrice.setText(dProduct.getFormattedUnitPrice());

        btnContinue.setText("Continue");
        btnCheckout.setText("Checkout");


        builder.setView(dialogView).setMessage("");

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                dismiss();
                Intent i = new Intent(mContext, CartPage.class);
                startActivity(i);
 */           }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Home.shoppingCart.addCartItem(new CartItem(dProduct, 1));
                Toast.makeText(mContext, dProduct.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(quantity.getText().equals("0"))
                {
                    Toast.makeText(mContext, dProduct.getName() + " cannot be deleted", Toast.LENGTH_SHORT).show();
                    return;
                }
                Home.shoppingCart.deleteCartItem(new CartItem(dProduct, 1));
            }
        });

        return builder.create();

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    public void setContext(Context c){
        mContext = c;

    }

    // receive cart from the MainActivity
    public void sendProduct(Product product){
        dProduct = product;
    }

}

