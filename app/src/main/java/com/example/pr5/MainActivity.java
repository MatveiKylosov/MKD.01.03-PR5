package com.example.pr5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tv_timer;
    int time = 0;

    Timer timer;
    TimerTask mTimerTask;
    boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_timer = findViewById(R.id.textView2);
    }

    public void OnStart(View view){
        active = !active;

        if(active){
            if(timer != null)
                timer.cancel();

            timer = new Timer();
            mTimerTask = new MyTimerTask();

            timer.schedule(mTimerTask, 0, 1000);
            Button button = findViewById(R.id.button);
            button.setText("СТОП");
        }
        else{
            if(timer != null)
                timer.cancel();

            timer = null;

            Button button = findViewById(R.id.button);
            button.setText("СТОП");
        }
    }
    public void onClear(){
        if(timer != null)
            timer.cancel();

        timer = null;
        time = 0;
        tv_timer.setText("00:00:00");

        Button button = findViewById(R.id.button);
        button.setText("Начать");
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run(){
            String s_second = "";
            time++;

            int hour = time/60/60;
            int min = time/60 - (hour * 60);
            int second = time - (min * 60) - (hour * 60 * 60);

            if(second < 10)
                s_second = "0" + second;
            else
                s_second = String.valueOf(second);

            String s_min = "";
            if(min < 10)
                s_min = "0" + min;
            else
                s_min = String.valueOf(second);

            String s_hour = "";
            if(hour < 10)
                s_hour = "0" + hour;
            else
                s_hour = String.valueOf(hour);

            tv_timer.setText(s_hour + ":" + s_min + ":" + s_second);
        }
    }
}