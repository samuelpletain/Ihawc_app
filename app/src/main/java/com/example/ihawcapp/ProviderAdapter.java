package com.example.ihawcapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder> {
    private String[] localDataSet;
    private ArrayList<String> ids;
    private WeakReference<Activity> activity;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderAdapter.ViewHolder holder, int position) {
        holder.getTextView().setText(localDataSet[position]);
        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.get(), SingleResultActivity.class);
                intent.putExtra("ID", ids.get(position));
                Log.d("ProviderAdapter", ids.get(position));
                activity.get().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        View view;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);
            this.view = view;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public ProviderAdapter(String[] dataSet, ArrayList<String> ids, WeakReference<Activity> activity) {
        this.localDataSet = dataSet;
        this.ids = ids;
        this.activity = activity;
    }
}
