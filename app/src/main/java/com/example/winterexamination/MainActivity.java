package com.example.winterexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.winterexamination.View.MainView.LoginActivity;

//http://bihu.jay86.com/

public class MainActivity extends AppCompatActivity {
    private final String TAG = "*****Main*****";
    private Handler handler;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            MainActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        handler =new Handler();
        handler.postDelayed(runnable,1000);
    }

}
