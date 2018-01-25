package com.strobertchs.finalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.strobertchs.finalproject.model.CartItem;
import com.strobertchs.finalproject.model.Product;

import java.text.NumberFormat;

/**
 * DialogProduct is displayed after user clicks on a menu item in the SelectedMenu page.
 * @author jenny
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
        final TextView cartItemPrice = (TextView) dialogView.findViewById(R.id.unitPrice);
        final TextView quantity = (TextView) dialogView.findViewById(R.id.quantity);
        TextView prodPrice = (TextView) dialogView.findViewById(R.id.prodPrice);
        TextView txtPrice = (TextView) dialogView.findViewById(R.id.textView3);
        TextView txtQuantity = (TextView) dialogView.findViewById(R.id.textView8);

        txtPrice.setText("Price");
        txtQuantity.setText("Quantity");
        prodName.setText(dProduct.getName());
        subdescription.setText(dProduct.getSubDescription());
        prodPrice.setText(dProduct.getFormattedUnitPrice());

        //Get the price and quantity of the cartItem for this product in shopping cart
        String tPrice = Home.SHOPPING_CART.getCartItemFormattedPrice(this.dProduct);
        int tQuantity = Home.SHOPPING_CART.getCartItemQuantity(this.dProduct);
        cartItemPrice.setText(tPrice);
        quantity.setText(String.valueOf(tQuantity));

        btnContinue.setText("Continue");
        btnCheckout.setText("Checkout");

        builder.setView(dialogView).setMessage("");

        //Add button click listener for continue button
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //Add button click listener for checkout button
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent i = new Intent(mContext, CartPage.class);
                startActivity(i);
            }
        });

        //Add button click listener for add product button
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //add one more product to shopping cart
                Home.SHOPPING_CART.addCartItem(new CartItem(dProduct, 1));
                //update quantity text view
                int countOfProd = Integer.parseInt(quantity.getText().toString());
                countOfProd++;
                quantity.setText(String.valueOf(countOfProd));
                cartItemPrice.setText(Home.SHOPPING_CART.getCartItemFormattedPrice(dProduct));
                Toast.makeText(mContext, "1" + dProduct.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        //Add button click listener for delete product button
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(quantity.getText().equals("0"))
                {
                    Toast.makeText(mContext, "No more "+dProduct.getName() + "in cart", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    //delete one product from shopping cart
                    Home.SHOPPING_CART.deleteCartItem(new CartItem(dProduct, 1));
                    //update quantity text view
                    int countOfProd = Integer.parseInt(quantity.getText().toString());
                    countOfProd--;
                    quantity.setText(String.valueOf(countOfProd));
                    cartItemPrice.setText(Home.SHOPPING_CART.getCartItemFormattedPrice(dProduct));
                    Toast.makeText(mContext, "1 " + dProduct.getName() + " was deleted.", Toast.LENGTH_SHORT).show();
                }
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

