package com.test.androidapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class EventPage extends AppCompatActivity {

    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    Button back, newArticle;
    RelativeLayout layout;

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

        newArticle = findViewById(R.id.addArticle);
        layout = findViewById(R.id.relativelayout_event_1);

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
        model.setLat(-6.175339);
        model.setLongitude(106.827279);
        model.setDescription("This is version of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean gravida nibh vel velit auctor aliquet....");
        models.add(model);

        model = new EventModel();
        model.setTitle("Schematics NLC");
        model.setDate("17 Sep 2020");
        model.setImage(R.drawable.nlc);
        model.setColor("#ffb321");
        model.setTags(new String[]{"#nutricia"});
        model.setLat(-7.989524);
        model.setLongitude(112.633156);
        model.setDescription("This is version of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean gravida nibh vel velit auctor aliquet....");
        models.add(model);

        model = new EventModel();
        model.setTitle("Schematics NST");
        model.setDate("20 Okt 2020");
        model.setImage(R.drawable.nst);
        model.setColor("#47ab6c");
        model.setTags(new String[]{"#nutricia", "#highlight F3", "#event"});
        model.setLat(0.536449);
        model.setLongitude(101.450787);
        model.setDescription("This is version of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean gravida nibh vel velit auctor aliquet....");
        models.add(model);

        model = new EventModel();
        model.setTitle("Schematics REEVA");
        model.setDate("25 Okt 2020");
        model.setImage(R.drawable.reeva);
        model.setColor("#02547d");
        model.setTags(new String[]{"#nutricia", "#highlight F3"});
        model.setLat(-8.715104);
        model.setLongitude(115.168826);
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

    public void onClickAddArticle(View view){
        layout.setVisibility(view.GONE);
        recyclerView.setVisibility(view.GONE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentMaps fragment = new FragmentMaps(getList());
        fragmentManager.beginTransaction().replace(R.id.eventcontainer, fragment).commit();
    }

    public void onBackEvent(View view) {
        super.onBackPressed();
    }
}
