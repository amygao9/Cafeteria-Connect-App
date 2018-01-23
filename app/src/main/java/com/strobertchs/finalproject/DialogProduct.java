package com.strobertchs.finalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by jenny on 2018-01-21.
 */

//public class DialogProduct extends DialogFragment{
//
//    private Cart dCart;
//    private Context mContext;
//    private CartAdapter cAdapter;
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        setContentView(R.layout.dialogue_product);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        NumberFormat formatter = NumberFormat.getCurrencyInstance();
//
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_cart, null);
//
//        Button btnContinue = (Button) dialogView.findViewById(R.id.continueShopping);
//        Button btnCheckout = (Button) dialogView.findViewById(R.id.checkout);
//        TextView txtTitle = (TextView) dialogView.findViewById(R.id.title);
//        TextView txtTaxlbl = (TextView) dialogView.findViewById(R.id.tax);
//        TextView txtTotallbl = (TextView) dialogView.findViewById(R.id.total);
//        TextView txtSubtotallbl = (TextView) dialogView.findViewById(R.id.subtotal);
//
//        TextView txtTotalVal = (TextView) dialogView.findViewById(R.id.totalVal);
//        TextView txtSubtotalVal = (TextView) dialogView.findViewById(R.id.subtotalVal);
//        TextView txtTaxVal = (TextView) dialogView.findViewById(R.id.taxVal);
//
//
//        txtTitle.setText("Shopping Cart");
//
//        txtTaxlbl.setText("Tax");
//        txtTotallbl.setText("Total");
//        txtSubtotallbl.setText("Subtotal");
//
//        txtSubtotalVal.setText(dCart.getFormattedSubtotal());
//        txtTaxVal.setText(dCart.getFormattedTotalTax());
//        txtTotalVal.setText(dCart.getFormattedTotal());
//
//
//        btnContinue.setText("Continue Shopping");
//        btnCheckout.setText("Checkout");
//
//
//        builder.setView(dialogView).setMessage("");
//        cAdapter = new CartAdapter();
//        ListView listCartItems = (ListView) dialogView.findViewById(R.id.cartItemsList);
//        listCartItems.setAdapter(cAdapter);
//
//        btnContinue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//        return builder.create();
//
//
//
//    }
//
//    public void setContext(Context c){
//        mContext = c;
//
//    }
//
//    // receive cart from the MainActivity
//    public void sendCart(Cart mCart){
//        dCart = mCart;
//    }
//
//    public class CartAdapter extends BaseAdapter {
//
//
//        List<CartItem> cartItemsList = dCart.getCartItemList();
//
//
//        public int getCount(){
//            return cartItemsList.size();
//        }
//
//        public CartItem getItem(int whichItem){
//            return cartItemsList.get(whichItem);
//        }
//
//        public long getItemId(int whichItem) {
//            return whichItem;
//        }
//
//
//
//        @Override
//        public View getView(final int whichItem, View view, ViewGroup viewGroup)   {
//
//            // Implement this method next
//
//            // Has view been inflated already
//            if(view == null) {
//
//                // If not, do so here
//                // First create a LayoutInflater
//                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                // Now instantiate view using inflater.inflate
//                // using the listitem layout
//                view = inflater.inflate(R.layout.cartitem, viewGroup,false);
//                // The false parameter is necessary
//                // because of the way that we want to use cartitem
//
//            }// End if
//
//            TextView txtName = (TextView)view.findViewById(R.id.name);
//            TextView txtUnitPrice = (TextView)view.findViewById(R.id.unitprice);
//            TextView txtQty = (TextView)view.findViewById(R.id.qty);
//            TextView txtSubtotal = (TextView)view.findViewById(R.id.subtotal);
//
//
//
//            CartItem tempCartItem = cartItemsList.get(whichItem);
//            txtName.setText(tempCartItem.getProductName());
//            txtQty.setText(Integer.toString(tempCartItem.getQuantity()));
//
//
//            txtUnitPrice.setText(tempCartItem.getSubDescription());
//            txtSubtotal.setText(tempCartItem.getFormattedPrice());
//
//            return view;
//        }
//
//
//
//    }
//
//}
