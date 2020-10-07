package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar MySeekBar;
    Button MyButton;
    TextView MyTextView;
    CountDownTimer CountDownTimers;

    Boolean ConterIsActive=false;



    public void Update(int progress)
    {

        int minute=(int) progress/60;
        int second=progress-minute*60;
        String SecondS=Integer.toString(second);
        if(second<=9)
        {
             SecondS="0"+SecondS;
        }

        MyTextView.setText(minute+":"+SecondS);
    }
    public void Count(View view)
    {
        if (ConterIsActive==false) {

            ConterIsActive = true;
            MySeekBar.setEnabled(false);
            MyButton.setText("Stop");
        CountDownTimers = new CountDownTimer(MySeekBar.getProgress() * 1000, 1000) {
                public void onTick(long milliUntilFinished) {
                    Update((int) milliUntilFinished / 1000);

                }

                public void onFinish() {

                    MyTextView.setText("0:00");
                    //Log.i("Timer","working");
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mp.start();

                }
            }.start();
        }
        else
        {
            MyTextView.setText("0:30");
            MySeekBar.setProgress(30);
            CountDownTimers.cancel();
            MyButton.setText("Go!");
            MySeekBar.setEnabled(true);
            ConterIsActive=false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySeekBar=(SeekBar)findViewById(R.id.MySeekBar);
        MyButton=(Button)findViewById(R.id.MyButton);
        MyTextView=(TextView)findViewById(R.id.MyTextView);

        MySeekBar.setProgress(30);
        MySeekBar.setMax(600);

        MySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
