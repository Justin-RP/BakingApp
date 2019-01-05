package com.example.gaiajustin.bakingapp.Details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.gaiajustin.bakingapp.R;

// DetailCardViewHolder Binds the views
public class DetailCardViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = DetailCardViewHolder.class.getSimpleName();
    public TextView detailTitle;
    public TextView detailStepNumber;

    public DetailCardViewHolder(@NonNull View itemView) {
        super(itemView);
        detailTitle = itemView.findViewById(R.id.detail_title);
        detailStepNumber = itemView.findViewById(R.id.detail_step);
    }

}
