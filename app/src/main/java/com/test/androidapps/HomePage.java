package com.test.androidapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends AppCompatActivity {

    private static final int EVENT_REQUEST = 1;
    private static final int GUEST_REQUEST = 2;

    DatabaseHelper myDB;
    TextView text_name;
    Button bEvent, bGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        myDB = new DatabaseHelper(this);

//      ----- if the guest table is empty then uncomment the code below -----

//        insertDefaultDatabase();

        text_name = findViewById(R.id.yourname);
        bEvent = findViewById(R.id.event);
        bGuest = findViewById(R.id.guest);

        Intent intent = getIntent();
        String nama = " " + intent.getStringExtra("NAME");
        text_name.setText(nama);
    }

//    ----- uncomment this function below if your guest table is empty -----

//    public void insertDefaultDatabase() {
//        myDB.insertData("Andi", "2014-01-01");
//        myDB.insertData("Budi", "2014-02-02");
//        myDB.insertData("Charlie", "2014-03-03");
//        myDB.insertData("Dede", "2014-06-06");
//        myDB.insertData("Joko", "2014-02-12");
//    }

    public void onEventClick(View view) {
        Intent intentEvent = new Intent(this, EventPage.class);
        startActivityForResult(intentEvent, EVENT_REQUEST);
    }

    public void onGuestClick(View view) {
        Intent intentGuest = new Intent(this, GuestPage.class);
        startActivityForResult(intentGuest, GUEST_REQUEST);
    }

    public boolean checkEven(int number) {
        if((number % 2) == 0) return true;
        return false;
    }

    public boolean checkOdd(int number) {
        if((number % 3) == 0) return true;
        return false;
    }

    private int convertBirthDate(String data){
        Date birth;
        int result;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birth = simpleDateFormat.parse(data);
            result = Integer.parseInt(birth.getDate()+"");
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EVENT_REQUEST) {
            if(resultCode == RESULT_OK) {
                String event_name = data.getStringExtra("EVENT");
                bEvent.setText(event_name);
            }
        }
        if(requestCode == GUEST_REQUEST) {
            if(resultCode == RESULT_OK) {
                String date = null;
                int temp;
                String guest_name = data.getStringExtra("GUEST");

                Cursor res = myDB.queryGuest(guest_name);
                bGuest.setText(guest_name);

                if(res.getCount() == 0) {
                    Toast.makeText(this, "No data in database", Toast.LENGTH_SHORT).show();
                }
                while(res.moveToNext()) {
                    date = res.getString(2);
                }

                temp = convertBirthDate(date);

                if (temp == 0) Toast.makeText(this, "No valid birthdate", Toast.LENGTH_SHORT).show();
                else {
                    if (checkEven(temp) && checkOdd(temp))
                        Toast.makeText(this, "iOS", Toast.LENGTH_SHORT).show();

                    else if (checkEven(temp))
                        Toast.makeText(this, "blackberry", Toast.LENGTH_SHORT).show();

                    else if (checkOdd(temp))
                        Toast.makeText(this, "android", Toast.LENGTH_SHORT).show();

                    else Toast.makeText(this, "feature phone", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
