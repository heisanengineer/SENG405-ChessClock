package com.c1811291_assignment2;
import static java.lang.Integer.parseInt;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Runnable runnable;
    Handler handler;
    EditText textinput;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    int i = 0;
    int second = 0;
    int minute = 0;
    int whichPlayer = 0;
    int j = 1;
    int maxMinute = 5;

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
        textinput = findViewById(R.id.textInput);

        button.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(false);
        button5.setEnabled(true);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                player1();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                player2();
            }
        });
    }

    public void change(View view){
        maxMinute = parseInt(textinput.getText().toString());
        button3.setText("CHANGED");
        button3.setBackgroundColor(Color.BLACK);
    }

    public void start (View view)
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (maxMinute > minute) 
                {
                    textView1.setText(""+second);
                    textView2.setText(""+minute);
                    second++;

                if (second == 60)
                {
                    minute++;
                    second = 0;
                }
                textView1.setText(""+second);
                textView2.setText(""+minute);
                handler.postDelayed(runnable,1000);
            }
            else
            {
                    stop(textView1);
                    button.setText("TIME IS UP!");
                    button.setBackgroundColor(Color.BLACK);
                    button2.setText("WIN!");
                    button2.setBackgroundColor(Color.RED);
                }
            }
        };handler.post(runnable); button4.setEnabled(true);
    }

    public void start2 (View view)
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (maxMinute > minute) 
                {
                    textView3.setText("" + second);
                    textView4.setText("" + minute);
                    second++;

                    if (second == 60) 
                    {
                        minute++;
                        second = 0;
                    }

                    textView3.setText("" + second);
                    textView4.setText("" + minute);
                    handler.postDelayed(runnable, 1000);

                }
                else
                {
                    stop(textView1);
                    button2.setText("TIME IS UP!");
                    button2.setBackgroundColor(Color.BLACK);
                    button.setText("WIN!");
                    button.setBackgroundColor(Color.RED);
                }
            }
        };handler.post(runnable); button4.setEnabled(true);
    }

    public void pause(View view)
    {
        button3.setEnabled(true);
        handler.removeCallbacks(runnable);

        if (j == 1)
        {
            button5.setText("PLAY");
            j = 2;
        }
        else if (j == 2)
        {
            button5.setText("PAUSE");
            j = 1;

            if (whichPlayer == 1) 
            {
                start(view);

            } 
            else if (whichPlayer == 2) 
            {
                start2(view);
            }
        }
    }

    public void stop(View view)
    {
        button4.setEnabled(true);
        handler.removeCallbacks(runnable);
        second = 0;
        minute = 0;
        i = 0;
        button5.setText("PAUSE");
        textView1.setText(""+second);
        textView2.setText(""+minute);
        textView3.setText(""+second);
        textView4.setText(""+minute);
        button.setText("START");
        button2.setText("START");
        button.setBackgroundColor(Color.rgb(255,87,34));
        button2.setBackgroundColor(Color.rgb(103,58,183));
    }

    public void player1(){
        i++;
        second = 00;
        minute = 0;
        textView1.setText(" "+second);
        textView2.setText(" "+minute);

        if (i == 1) 
        {
            start(textView1);
            button.setText("STOP");
            button2.setText("START");
            whichPlayer = 1;

        } 
        else if (i == 2) 
        {
            stop(textView1);
            button.setText("START");
            button2.setText("STOP");
            i = 1;
            start2(textView1);
            whichPlayer = 2;
        }
    }

    public void player2(){
        i++;
        second = 0;
        minute = 0;
        textView3.setText(""+second);
        textView4.setText(""+minute);

        if (i == 1) 
        {
            start2(textView1);
            button.setText("START");
            button2.setText("STOP");
            whichPlayer = 0;

        } 
        else if (i == 2) 
        {
            stop(textView1);
            button2.setText("START");
            button.setText("STOP");
            i = 1;
            start(textView1);
            whichPlayer = 1;
        }
    }
}