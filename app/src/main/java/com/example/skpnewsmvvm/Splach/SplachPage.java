package com.example.skpnewsmvvm.Splach;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skpnewsmvvm.MainActivity;
import com.example.skpnewsmvvm.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplachPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_page);



        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplachPage.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }


}
