package com.example.parkalot.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkalot.Booking;
import com.example.parkalot.R;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private ArrayList listdata;
    Context context;
    public MyListAdapter(Context context,ArrayList listdata) {
       this.listdata = listdata;
       this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rawlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        Context context  = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textView.setText(listdata[position].getDescription());
//        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.textView.setText((CharSequence) listdata.get(position));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Click",Toast.LENGTH_SHORT);
                Log.d("TAG1010", "onClick: ");
//                Intent i = new Intent(view.getContext(),Booking.class);

                view.getContext().startActivity(new Intent(view.getContext(),Booking.class));
            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           imageView = itemView.findViewById(R.id.image);
           textView = itemView.findViewById(R.id.text);
           layout = itemView.findViewById(R.id.layout);

        }
    }
}
