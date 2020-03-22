package com.test.androidapps;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nama;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dexter.withActivity(this).withPermissions(Arrays.asList(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();

        nama = findViewById(R.id.name);
        next = findViewById(R.id.next);
    }

    public void onClick(View view) {
        String text_name = nama.getText().toString().trim();
        String res = isPalindrome(text_name);
        Intent intentHome = new Intent(this, HomePage.class);
        intentHome.putExtra("NAME", text_name);
        intentHome.putExtra("PALINDROME", res);
        startActivity(intentHome);
    }

    private String isPalindrome(String name){
        String msg;
        String text = name.replaceAll("\\s+","");   //for erase the whitespace and space
        String reverse = new StringBuffer(text).reverse().toString();
        if (text.equalsIgnoreCase(reverse)) msg = name + " isPalindrome";
        else msg = name + " not palindrome";
        return msg;
    }
}
