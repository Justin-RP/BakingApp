package com.example.gaiajustin.bakingapp.Nav;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gaiajustin.bakingapp.R;

// NavCardViewHolder Binds the views
public class NavCardViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = NavCardViewHolder.class.getSimpleName();
    public TextView detailTitle;
    public TextView detailStepNumber;

    public NavCardViewHolder(@NonNull View itemView) {
        super(itemView);
        detailTitle = itemView.findViewById(R.id.nav_title);
        detailStepNumber = itemView.findViewById(R.id.nav_step);
    }

}
