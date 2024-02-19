package com.example.patuvanje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class prethodniAdapter extends RecyclerView.Adapter<prethodniAdapter.ViewHolder> {
    private List<String> myList;
    private int rowLayout;
    private Context mContext;
    // Референца на views за секој податок
// Комплексни податоци може да бараат повеќе views per item
// Пристап до сите views за податок се дефинира во view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myName;
        public ViewHolder(View itemView) {
            super(itemView);
            myName = (TextView) itemView.findViewById(R.id.Name);

        }
    }
    // конструктор
    public prethodniAdapter(List<String> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }
    // Креирање нови views (повикано од layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    // Замена на содржината во view (повикано од layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);
        viewHolder.myName.setText(entry);

    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}

