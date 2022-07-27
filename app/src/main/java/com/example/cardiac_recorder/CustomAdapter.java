package com.example.cardiac_recorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    @NonNull

    private Context context;
    Activity activity;
    private ArrayList data_id1,date,time,systolic,diastolic,pulse,comments;


    CustomAdapter(Activity activity,Context context1, ArrayList data_id2,ArrayList systolic1, ArrayList diastolic1, ArrayList pulse1, ArrayList date1, ArrayList time1, ArrayList comment1) {
        this.activity= activity;
        this.context = context1;
        this.data_id1 = data_id2;
        this.systolic = systolic1;
        this.diastolic = diastolic1;
        this.pulse = pulse1;
        this.date = date1;
        this.time = time1;
        this.comments = comment1;
    }


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.data_id.setText(String.valueOf(data_id1.get(position)));
        holder.systolic1.setText(String.valueOf(systolic.get(position)));
        holder.diastolic1.setText(String.valueOf(diastolic.get(position)));
        holder.pulse1.setText(String.valueOf(pulse.get(position)));
        holder.date1.setText(String.valueOf(date.get(position)));
        holder.time1.setText(String.valueOf(time.get(position)));
        holder.comment1.setText(String.valueOf(comments.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateAndDeleteActivaty.class);
                intent.putExtra("dataid", String.valueOf(data_id1.get(position)));
                intent.putExtra("systolic", String.valueOf(systolic.get(position)));
                intent.putExtra("diastolic", String.valueOf(diastolic.get(position)));
                intent.putExtra("pulse", String.valueOf(pulse.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("time", String.valueOf(time.get(position)));
                intent.putExtra("comment", String.valueOf(comments.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data_id1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView data_id, systolic1, diastolic1, pulse1, date1,time1, comment1;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data_id = itemView.findViewById(R.id.data_id);
            date1 = itemView.findViewById(R.id.date1);
            time1 = itemView.findViewById(R.id.time1);
            systolic1 = itemView.findViewById(R.id.systolic1);
            diastolic1 = itemView.findViewById(R.id.diastolic1);
            pulse1 = itemView.findViewById(R.id.pulse1);
            comment1 = itemView.findViewById(R.id.comment1);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}
