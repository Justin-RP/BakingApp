package com.example.gaiajustin.bakingapp.CakeGrid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.gaiajustin.bakingapp.R;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = ProductCardViewHolder.class.getSimpleName();
    public NetworkImageView productImage;
    public TextView productTitle;

    public ProductCardViewHolder (@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);

        // TODO SHOULD I DELETE THIS CODE, Not part of viewholder
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: productImage clicked");
            }
        });
    }

}
