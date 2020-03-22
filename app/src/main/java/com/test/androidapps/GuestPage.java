package com.test.androidapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GuestPage extends AppCompatActivity implements View.OnClickListener{

    private CardView card1, card2, card3, card4, card5;
    private TextView text1, text2, text3, text4, text5;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page);

        gridLayout = findViewById(R.id.gridlayout);

        swipeRefreshLayout = findViewById(R.id.swiperefreshitem);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        bindCard();
        bindText();
    }

    private void bindCard() {
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
    }

    private void bindText(){
        text1 = findViewById(R.id.guest1);
        text2 = findViewById(R.id.guest2);
        text3 = findViewById(R.id.guest3);
        text4 = findViewById(R.id.guest4);
        text5 = findViewById(R.id.guest5);
    }

    private void sendGuestName(String name){
        Intent intent = new Intent();
        intent.putExtra("GUEST", name);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        String name;

        switch (v.getId()) {
            case R.id.card1 :
                name = text1.getText().toString().trim();
                sendGuestName(name);
                break;
            case R.id.card2 :
                name = text2.getText().toString().trim();
                sendGuestName(name);
                break;
            case R.id.card3 :
                name = text3.getText().toString().trim();
                sendGuestName(name);
                break;
            case R.id.card4 :
                name = text4.getText().toString().trim();
                sendGuestName(name);
                break;
            case R.id.card5 :
                name = text5.getText().toString().trim();
                sendGuestName(name);
                break;
        }
    }

    public void onBackGuest(View view){
        super.onBackPressed();
    }
}
