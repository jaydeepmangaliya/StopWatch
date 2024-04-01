package com.example.stopwatch_p;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
     private  int second;
     private boolean running;
     private  boolean wasRuning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasRuning =savedInstanceState.getBoolean("wasRuning");

        }
        runingTimer();
    }

    private void runingTimer() {
        final TextView timeview = findViewById(R.id.Time_time);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hr = second/3600;
                int mit =(second/3600)/60;
                int sec = second%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d",hr,mit,sec
                );
                timeview.setText(time);
                if(running){
                    second++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRuning=running;
        running=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRuning){
            running=true;
        }
    }

    public void onClickStart(View view){
          running=true;
    }
    public void onClickStop(View view){
        running = false;

    }
    public void onClickRestart(View view){
        running = false;
        second =0;


    }
}