package com.test.androidapps;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
        String[] tagsList = models.get(position).getTags();
        LinearLayout layoutTags = holder.layoutTags;
        addTags(tagsList, layoutTags);
        holder.eventTitle.setText(models.get(position).getTitle());
        holder.eventTitle.setTextColor(Color.parseColor(models.get(position).getColor()));
        holder.eventDate.setText(models.get(position).getDate());
        holder.imageEvent.setImageResource(models.get(position).getImage());
        holder.eventDesc.setText(models.get(position).getDescription());
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

    public void addTags(String[] tags, LinearLayout layout) {
        for (int i=0; i<tags.length; i++) {
            Button newbtn = new Button(context);
            newbtn.setText(tags[i]);
            newbtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            newbtn.setAllCaps(false);
            newbtn.setBackgroundResource(R.drawable.custom_tag);

            newbtn.setPadding(dpToPx(5), 0, dpToPx(5), 0);
            newbtn.setMinWidth(0);
            newbtn.setMinimumWidth(10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.WRAP_CONTENT, dpToPx(20));
            params.setMargins(10,5,10,0);
            newbtn.setLayoutParams(params);
            layout.addView(newbtn);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
