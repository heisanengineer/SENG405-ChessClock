package com.c1811291_assignment2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Runnable runnable;
    Handler handler;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    int i;
    int second;
    int minute;
    int whichPlayer=0;
    int j=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textTop1);
        textView2 = findViewById(R.id.textTop2);
        textView4 = findViewById(R.id.textBot1);
        textView3 = findViewById(R.id.textBot2);
        button = findViewById(R.id.buttonTop);
        button2 = findViewById(R.id.buttonBot);
        button3 = findViewById(R.id.buttonLeft);
        button4 = findViewById(R.id.buttonRight);
        button5 = findViewById(R.id.buttonCenter);

        button.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);

        second =0;
        minute=0;
        i=0;

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                i++;
                second=0;
                minute=0;
                textView1.setText(" "+second);
                textView2.setText(" "+minute);

                if (i == 1) {
                    start(textView1);
                    button.setText("STOP");
                    button2.setText("START");
                    whichPlayer=1;

                } else if (i == 2) {
                    stop(textView1);
                    button.setText("START");
                    button2.setText("STOP");
                    i = 1;
                    start2(textView1);
                    whichPlayer=2;
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                i++;
                second=0;
                minute=0;
                textView3.setText(""+second);
                textView4.setText(""+minute);

                if (i == 1) {
                    start2(textView1);
                    button.setText("START");
                    button2.setText("STOP");
                    whichPlayer=0;

                } else if (i == 2) {
                    stop(textView1);
                    button2.setText("START");
                    button.setText("STOP");
                    i = 1;
                    start(textView1);
                    whichPlayer =1;
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                second=0;
                minute=0;
                start(textView1);
                button.setText("START");
                button2.setText("STOP");
                i++;
            }
        });
    }


    public void start (View view)
    {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView1.setText(""+second);
                textView2.setText(""+minute);
                second++;
                if(second==60){
                    minute++;
                    second=0;
                }
                textView1.setText(""+second);
                textView2.setText(""+minute);
                handler.postDelayed(runnable,1000);
            }
        };handler.post(runnable);
    }

    public void start2 (View view)
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView3.setText(""+second);
                textView4.setText(""+minute);
                second++;
                if(second==60){
                    minute++;
                    second=0;
                }
                textView3.setText(""+second);
                textView4.setText(""+minute);
                handler.postDelayed(runnable,1000);
            }
        };handler.post(runnable);

    }

    public void pause(View view)
    {

        handler.removeCallbacks(runnable);

        if (j==1){
            button5.setText("PLAY");
            j=2;
        }else if(j==2){
            button5.setText("PAUSE");
            j=1;

            if (whichPlayer == 1) {
                start(textView1);

            } else if (whichPlayer == 2) {
                start2(textView3);
            }
        }

    }

    public void stop(View view)
    {
        handler.removeCallbacks(runnable);
        second=0;
        minute=0;
        button.setText("START");
        button2.setText("START");
        i=0;
        button5.setText("PAUSE");
        textView1.setText(""+second);
        textView2.setText(""+minute);
        textView3.setText(""+second);
        textView4.setText(""+minute);
    }



}