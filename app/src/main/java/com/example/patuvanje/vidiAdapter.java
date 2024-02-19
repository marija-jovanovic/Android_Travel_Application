package com.example.patuvanje;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class vidiAdapter extends RecyclerView.Adapter<vidiAdapter.ViewHolder> {
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
    public vidiAdapter(List<String> myList, int rowLayout, Context context) {
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
              //  v.getContext().startActivity(new Intent(v.getContext(), NVirementEmmeteur.class));
              //  context = viewHolder.myName.getContext();
                String k = ViewTripActivity.korisnik;
                Toast.makeText(mContext, k, Toast.LENGTH_SHORT).show();
                String niza [] = (String.valueOf(viewHolder.myName.getText())).split(" ");
                Intent i = new Intent(v.getContext(), PatnikOpcii.class);
                i.putExtra("id", niza[1]); //id-to na vozenjeto
                i.putExtra("korisnik",k);
               // context.startActivity(i);
                v.getContext().startActivity(i);
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
