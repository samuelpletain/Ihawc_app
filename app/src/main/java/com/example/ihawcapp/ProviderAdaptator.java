package com.example.ihawcapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProviderAdaptator extends RecyclerView.Adapter<ProviderAdaptator.ViewHolder> {
    private List<Provider> localDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // attributes to implement

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            //Add stuff here
        }
    }

    public ProviderAdaptator(List<Provider> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ProviderAdaptator.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        //To modify
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ProviderAdaptator.ViewHolder holder, int position) {
        //To implement
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
