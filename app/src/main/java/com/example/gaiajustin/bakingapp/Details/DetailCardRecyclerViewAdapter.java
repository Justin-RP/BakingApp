package com.example.gaiajustin.bakingapp.Details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaiajustin.bakingapp.CakeGrid.ProductCardViewHolder;
import com.example.gaiajustin.bakingapp.ImageRequester;
import com.example.gaiajustin.bakingapp.R;
import com.example.gaiajustin.bakingapp.database.Cake;

import java.util.List;

public class DetailCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder>{
    private List<Cake> productList;
    private ImageRequester imageRequester;

    DetailCardRecyclerViewAdapter(List<Cake> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ba_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            Cake cake = productList.get(position);
            holder.productTitle.setText(cake.getName());
            imageRequester.setImageFromUrl(holder.productImage, cake.getImageURL());
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
