package com.example.pharases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buttonTapped(View view )
    {
       int id=view.getId();
        String ourId="";
        ourId=view.getResources().getResourceEntryName(id);

        int resourseId=getResources().getIdentifier(ourId,"raw","com.example.pharases");
         MediaPlayer mp= MediaPlayer.create(this,resourseId);
        mp.start();
        Log.i("button tapped", ourId);
    }
}
