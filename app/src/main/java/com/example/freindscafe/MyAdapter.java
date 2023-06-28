package com.example.freindscafe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
   Context context;
    ArrayList list;
    Random random;MyAdapter adapter;

    RecyclerView recyclerView;
    ArrayList<FoodData> arrayList;



    public MyAdapter(Context context, ArrayList list){
     this.context=context;
        this.list = list;


          }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.myfooddata,parent,false);
         return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodData food= (FoodData) list.get(position);
        holder.name.setText(food.getname());
        holder.mobile.setText(food.getMobile());
         holder.bill.setText(food.getBill());
        holder.date.setText(food.getdate());
        holder.table.setText(food.getTable());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete this row?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        list.remove(position);
                        if (adapter != null) {
                            list.remove(position);
                            adapter.notifyItemRemoved(position);
                        }

                        String key=food.getname();

                    }
                });
                builder.show();
            }
        });
        }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView  name,mobile,bill,date,table;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.dishname);
            mobile=itemView.findViewById(R.id.custmob);
            bill=itemView.findViewById(R.id.custbill);
            date=itemView.findViewById(R.id.custdate);
            table=itemView.findViewById(R.id.custtable);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
