package com.example.patuvanje;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class patnikAdapter extends RecyclerView.Adapter<patnikAdapter.ViewHolder> {
    private List<String> myList;
    private int rowLayout;
    private Context mContext;
    private Context context;

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
    public patnikAdapter(List<String> myList, int rowLayout, Context context) {
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
        viewHolder.myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = viewHolder.myName.getContext();
               // TextView tv = (TextView) v;
                String niza [] = (String.valueOf(viewHolder.myName.getText())).split(" ");
               // String niza [] = (String.valueOf(tv.getText())).split(" ");
                //Toast.makeText(mContext, tv.getText(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, PrijaviPatuvanje.class);
                i.putExtra("id", niza[1]); //id-to
                i.putExtra("mesta", niza[15]);
                context.startActivity(i);
               // ((Activity)context).finish();

            }
        });

    }
    // Пресметка на големината на податочното множество (повикано од
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
