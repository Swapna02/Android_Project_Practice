package com.example.time_table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView MyListBar;

    public void getProgress(int TimeTable)
    {
      //  int TimeTable=10;
        ArrayList<String> arrayList=new ArrayList<String>();
        for (int i=1;i<=10;i++)
        {
            arrayList.add(Integer.toString(i*TimeTable));
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        MyListBar.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar MySeekBar = (SeekBar)findViewById(R.id.MySeekBar);
        MyListBar =(ListView)findViewById(R.id.MyListView);

        MySeekBar.setMax(20);
        MySeekBar.setProgress(10);

        MySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min=1;
                int ProgressNote;
                if(progress<min)
                {
                    ProgressNote=min;
                    MySeekBar.setProgress(min);
                }
                else
                {
                    ProgressNote=progress;
                }
               // Log.i("seekbar values are",Integer.toString(ProgressNote));
                getProgress(ProgressNote);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        getProgress(10);

    }
}
