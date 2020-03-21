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
        checkDatabase();

        text_name = findViewById(R.id.yourname);
        bEvent = findViewById(R.id.event);
        bGuest = findViewById(R.id.guest);

        Intent intent = getIntent();
        String nama = " " + intent.getStringExtra("NAME");
        String isPalindrome = intent.getStringExtra("PALINDROME");
        text_name.setText(nama);

        openDialog(isPalindrome);
    }

    private void openDialog(String isPalindrome){
        PalindromeDialog alertPalindromeDialog = new PalindromeDialog(isPalindrome);
        alertPalindromeDialog.show(getSupportFragmentManager(), "isPalindrome");
    }

    private void checkDatabase(){
        Cursor res = myDB.queryAll();
        if(res.getCount() == 0) {
            myDB.insertData("Andi", "2014-01-01");
            myDB.insertData("Budi", "2014-02-02");
            myDB.insertData("Charlie", "2014-03-03");
            myDB.insertData("Dede", "2014-06-06");
            myDB.insertData("Joko", "2014-02-12");
        }
    }

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

    private Date convertBirth(String data){
        Date birth;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birth = simpleDateFormat.parse(data);
//            result = Integer.parseInt(birth.getDate()+"");
            return birth;
        } catch (ParseException e) {
            e.printStackTrace();
            return birth = null;
        }
    }

    private int getDate(String data){
        int result;
        Date birth = convertBirth(data);
        result = Integer.parseInt(birth.getDate()+"");
        return result;
    }

    private String isPrime(String data){
        Date birth = convertBirth(data);
        int number = Integer.parseInt(birth.getMonth()+"") + 1;
        boolean isprime = true;
        String msg = null;
        if(number > 1){
            for(int i=2; i<=Math.sqrt(number); i++) {
                if(number % i ==0 ) {
                    isprime = false;
                    msg = number+" is not prime";
                    break;
                }
            }
            if(isprime) msg = number+" is prime";
        }
        else {
            msg = number+" is not prime";
        }
        return msg;
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

                String msg = isPrime(date);
                temp = getDate(date);

                if (temp == -1) Toast.makeText(this, "No valid birthdate", Toast.LENGTH_SHORT).show();
                else {
                    if (checkEven(temp) && checkOdd(temp))
                        Toast.makeText(this, "iOS, " + msg, Toast.LENGTH_LONG).show();

                    else if (checkEven(temp))
                        Toast.makeText(this, "blackberry, " + msg, Toast.LENGTH_LONG).show();

                    else if (checkOdd(temp))
                        Toast.makeText(this, "android, " + msg, Toast.LENGTH_LONG).show();

                    else Toast.makeText(this, "feature phone, " + msg, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
