package com.test.androidapps;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView imageEvent;
    TextView eventTitle, eventDate;
    EventClickListener eventClickListener;

    EventHolder(@NonNull View itemView) {
        super(itemView);

        imageEvent = itemView.findViewById(R.id.imageIv);
        eventTitle = itemView.findViewById(R.id.titleTv);
        eventDate = itemView.findViewById(R.id.dateTv);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.eventClickListener.onItemClickListener(v, getLayoutPosition());
    }

    public void setEventClickListener(EventClickListener listener) {
        this.eventClickListener = listener;
    }
}
