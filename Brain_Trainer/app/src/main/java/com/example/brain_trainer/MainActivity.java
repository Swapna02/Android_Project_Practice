package com.example.brain_trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView ScoreTextView;
    TextView TimerTTextView;
    TextView CorrectTextView;
    TextView SumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button PlayAgainButton;
    RelativeLayout Relativelayout;
    Button GoButton;
    int locationCorrectOption;
    int wrongOptions;
    int score;
    int NumofQutn;

    public void PlayAgain(View view)
    {
        score=0;
        NumofQutn=0;
        TimerTTextView.setText("30s");
        ScoreTextView.setText("0/0");
        CorrectTextView.setText("");
        PlayAgainButton.setVisibility(View.INVISIBLE);
        UpdateQuestion();

        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                TimerTTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                PlayAgainButton.setVisibility(View.VISIBLE);
                TimerTTextView.setText("0s");
                CorrectTextView.setText("Your Score is: "+Integer.toString(score) +"/"+Integer.toString(NumofQutn));
            }

        }.start();

    }
    ArrayList<Integer> options;

    {
        options = new ArrayList<Integer>();
    }

    public void UpdateQuestion()
    {


        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        SumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationCorrectOption=rand.nextInt(4);

        options.clear();

        for (int i=0;i<4;i++)
        {
            if(i==locationCorrectOption)
            {
                options.add( a + b );
            }
            else
            {
                wrongOptions=rand.nextInt(41);
                while (wrongOptions== a + b )
                {
                    wrongOptions=rand.nextInt(41);
                }
                options.add(wrongOptions);
            }
        }
        button0.setText(Integer.toString(options.get(0)));
        button1.setText(Integer.toString(options.get(1)));
        button2.setText(Integer.toString(options.get(2)));
        button3.setText(Integer.toString(options.get(3)));

    }
    public void StartGame(View view)
    {
        GoButton.setVisibility(View.INVISIBLE);
        Relativelayout.setVisibility(RelativeLayout.VISIBLE);
    }

    public void ChooseOption(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationCorrectOption)))
        {
            score++;
            CorrectTextView.setText("Correct!");

        }
        else
        {
            CorrectTextView.setText("Wrong!");

        }
        NumofQutn++;
        ScoreTextView.setText((Integer.toString(score)+"/"+Integer.toString(NumofQutn)));
        UpdateQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoButton=(Button)findViewById(R.id.GoButton);
        ScoreTextView= (TextView) findViewById(R.id.ScoreTextView);
        TimerTTextView=(TextView)findViewById(R.id.TimerTextView);
        CorrectTextView=(TextView)findViewById(R.id.CorrectTextView);
        SumTextView=(TextView)findViewById(R.id.SumTextView);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        PlayAgainButton=(Button)findViewById(R.id.PlayAgainButton);
        Relativelayout=(RelativeLayout)findViewById(R.id.Relativelayout);

       // UpdateQuestion();
        PlayAgain(findViewById(R.id.PlayAgainButton));


    }
}
