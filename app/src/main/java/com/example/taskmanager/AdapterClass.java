package com.example.taskmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends ArrayAdapter<Data> {

    Context ctx;

    public AdapterClass(@NonNull Context context,@NonNull ArrayList<Data> objects) {
        super(context, R.layout.model, objects);
        this.ctx=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model,parent,false);

        TextView nomtache= convertView.findViewById(R.id.nomTask);
        TextView tache= convertView.findViewById(R.id.task);
        TextView date= convertView.findViewById(R.id.date);

        Data task = getItem(position);
        tache.setText(task.getTask());
        nomtache.setText(task.getNomtask());
        date.setText(task.getDate());


        return convertView;
    }
}
