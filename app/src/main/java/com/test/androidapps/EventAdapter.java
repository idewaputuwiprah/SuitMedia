package com.test.androidapps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventHolder> {

    Context context;
    ArrayList<EventModel> models;

    public EventAdapter(Context con, ArrayList<EventModel> models) {
        this.context = con;
        this.models = models;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, null);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.eventTitle.setText(models.get(position).getTitle());
        holder.eventTitle.setTextColor(Color.parseColor(models.get(position).getColor()));
        holder.eventDate.setText(models.get(position).getDate());
        holder.imageEvent.setImageResource(models.get(position).getImage());
        holder.setEventClickListener(new EventClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String getTitle = models.get(position).getTitle().trim();
                Intent intent = new Intent("custom-message");
                intent.putExtra("EVENT_TITLE", getTitle);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
