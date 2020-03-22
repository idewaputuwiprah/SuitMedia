package com.test.androidapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.ArrayList;

public class EventPage extends AppCompatActivity {

    RecyclerView recyclerView;
    EventAdapter eventAdapter;

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("EVENT_TITLE");
//            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            sendEventName(name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventAdapter = new EventAdapter(this, getList());
        recyclerView.setAdapter(eventAdapter);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-message"));
    }

    private ArrayList<EventModel> getList() {
        ArrayList<EventModel> models = new ArrayList<>();

        EventModel model = new EventModel();
        model.setTitle("Schematics NPC");
        model.setDate("17 Sep 2020");
        model.setImage(R.drawable.npc);
        model.setColor("#ed313b");
        model.setTags(new String[]{"#nutricia", "#highlight F3"});
        model.setDescription("This is version of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean gravida nibh vel velit auctor aliquet....");
        models.add(model);

        model = new EventModel();
        model.setTitle("Schematics NLC");
        model.setDate("17 Sep 2020");
        model.setImage(R.drawable.nlc);
        model.setColor("#ffb321");
        model.setTags(new String[]{"#nutricia"});
        model.setDescription("This is version of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean gravida nibh vel velit auctor aliquet....");
        models.add(model);

        model = new EventModel();
        model.setTitle("Schematics NST");
        model.setDate("20 Okt 2020");
        model.setImage(R.drawable.nst);
        model.setColor("#47ab6c");
        model.setTags(new String[]{"#nutricia", "#highlight F3", "#event"});
        model.setDescription("This is version of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean gravida nibh vel velit auctor aliquet....");
        models.add(model);

        model = new EventModel();
        model.setTitle("Schematics REEVA");
        model.setDate("25 Okt 2020");
        model.setImage(R.drawable.reeva);
        model.setColor("#02547d");
        model.setTags(new String[]{"#nutricia", "#highlight F3"});
        model.setDescription("This is version of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean gravida nibh vel velit auctor aliquet....");
        models.add(model);

        return models;
    }

    private void sendEventName(String title){
        Intent intent = new Intent();
        intent.putExtra("EVENT", title);
        setResult(RESULT_OK, intent);
        finish();
    }
}
