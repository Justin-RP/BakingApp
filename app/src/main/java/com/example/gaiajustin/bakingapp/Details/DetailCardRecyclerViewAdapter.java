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
import com.example.gaiajustin.bakingapp.database.Step;

import java.util.List;

public class DetailCardRecyclerViewAdapter extends RecyclerView.Adapter<DetailCardViewHolder>{
    private List<Step> stepList;

    DetailCardRecyclerViewAdapter(List<Step> stepList) {
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public DetailCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ba_detail_card, parent, false);
        return new DetailCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCardViewHolder holder, int position) {
        if (stepList != null && position < stepList.size()) {
            // TODO DISPLAY TEXT HERE
            Step step = stepList.get(position);
            holder.detailTitle.setText(step.getShortDesc());
            int displayPosition = position + 1;
            holder.detailStepNumber.setText("Step " + displayPosition);
        }
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

}
