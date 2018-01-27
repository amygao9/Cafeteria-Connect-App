package com.strobertchs.finalproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.strobertchs.finalproject.DialogProduct;
import com.strobertchs.finalproject.R;
import com.strobertchs.finalproject.SelectedMenu;
import com.strobertchs.finalproject.model.Product;

import java.util.List;

/**
 * ProductAdapter for rendering SelectedMenuList view.
 * @Author jenny
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    private SelectedMenu selectedMenu;

    public ProductAdapter(SelectedMenu selectedMenu, SelectedMenu pActivity, List<Product> pList) {
        super(pActivity, 0, pList);
        this.selectedMenu = selectedMenu;
    }

    public class Holder {
        TextView txtName;
        TextView txtPrice;
        ImageView prodImage;
        public void populateHolder(View view) {
            txtName = (TextView) view.findViewById(R.id.name);
            txtPrice = (TextView) view.findViewById(R.id.unitPrice);
            prodImage = (ImageView) view.findViewById(R.id.imageId);
        }
    }

    @Override
    public View getView(final int whichItem, View convertView, ViewGroup viewGroup) {
        Holder holder = new Holder();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product, viewGroup, false);
        }
        holder.populateHolder(convertView);

        Product tempProduct = getItem(whichItem);
        holder.txtName.setText(tempProduct.getName());
        holder.txtPrice.setText(tempProduct.getFormattedUnitPrice());
        Picasso.with(getContext()).load(tempProduct.getImageId()).into(holder.prodImage);

        // make the addToCart a clickable action
        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create the dialog for showing prouct details
                DialogProduct dialog = new DialogProduct();
                dialog.sendProduct(getItem(whichItem));
                dialog.setContext(getContext());
                dialog.show(selectedMenu.getFragmentManager(), "123");
            }
        });

        return convertView;
    }


}
