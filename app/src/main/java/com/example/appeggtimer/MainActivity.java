package com.example.appeggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Button button;
    boolean counter=false;
    CountDownTimer countDownTimer;

    public void resetTimer()
    {
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go");
        counter=false;
    }

    public void buttonClicked(View view)
    {
        if (counter)
        {
           resetTimer();
        }
        else{
        counter=true;
        seekBar.setEnabled(false);
        button.setText("Stop");
        countDownTimer = new CountDownTimer(seekBar.getProgress()*1000+100,1000)
        {
            @Override
            public void onTick(long l) {
                updateTimer((int)l/1000);
            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.guitar);
                mediaPlayer.start();
                resetTimer();
            }
        }.start();

        }
    }

    public void updateTimer(int secondsLeft)
    {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes*60);

        String secondsstring = Integer.toString(seconds);

        if (seconds<=9)
        {
            secondsstring="0"+secondsstring;
        }

        textView.setText(Integer.toString(minutes)+":"+secondsstring);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        textView =(TextView) findViewById(R.id.timerTextView);
        button=(Button) findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               updateTimer(i);
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