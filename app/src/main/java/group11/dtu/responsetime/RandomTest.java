package group11.dtu.responsetime;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class RandomTest extends AppCompatActivity {

    private RelativeLayout bgRelativeLayout;
    private Button click_button, slow_button, fast_button, perfect_button;
    private TextView responseTime, instruct;

    android.os.Handler handler = new android.os.Handler();
    Runnable runnable;

    Random delay= new Random();
    int Low = 0;
    int High = 1001;
    int lagDelay = delay.nextInt(High-Low) + Low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_test);

        bgRelativeLayout = (RelativeLayout) findViewById(R.id.random_test);
        click_button = (Button) findViewById(R.id.button7);
        slow_button = (Button) findViewById(R.id.button);
        fast_button = (Button) findViewById(R.id.button3);
        perfect_button = (Button) findViewById(R.id.button2);
        responseTime = (TextView) findViewById(R.id.textView4);
        instruct = (TextView) findViewById(R.id.instruction);
        //Disable buttons (wait till click)
        slow_button.setEnabled(false);
        fast_button.setEnabled(false);
        perfect_button.setEnabled(false);

    }

    public void changeBackground() {
        bgRelativeLayout.setBackgroundColor(Color.parseColor("#ADD8E6"));
        responseTime.setVisibility(View.VISIBLE);
        instruct.setVisibility(View.INVISIBLE);
        click_button.setEnabled(false);
        slow_button.setEnabled(true);
        fast_button.setEnabled(true);
        perfect_button.setEnabled(true);
        responseTime.setText("Did you experience lag?");
    }

    public void tooFast(View view) {
        click_button.setEnabled(true);
        instruct.setVisibility(View.INVISIBLE);
        slow_button.setEnabled(false);
        fast_button.setEnabled(false);
        perfect_button.setEnabled(false);
        responseTime.setVisibility(View.INVISIBLE);

        lagDelay += 100;
        click(view);
    }

    public void tooSlow(View view) {
        click_button.setEnabled(true);
        instruct.setVisibility(View.INVISIBLE);
        slow_button.setEnabled(false);
        fast_button.setEnabled(false);
        perfect_button.setEnabled(false);
        responseTime.setVisibility(View.INVISIBLE);

        if(lagDelay > 0) {
            lagDelay -= 100;
            click(view);
        } else {
            perfect(view);
        }
    }

    public void perfect(View view) {
        responseTime.setVisibility(View.VISIBLE);
        responseTime.setText("" + lagDelay + " ms");
        instruct.setVisibility(View.INVISIBLE);
        click_button.setVisibility(View.VISIBLE);
        click_button.setText("↺ RESTART");
        click_button.setEnabled(true);
        slow_button.setEnabled(false);
        fast_button.setEnabled(false);
        perfect_button.setEnabled(false);


        click_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RandomTest.this, MainActivity.class);

                RandomTest.this.startActivity(myIntent);

            }
        });

    }

    public void click(View view) {
        click_button.setVisibility(View.INVISIBLE);


        bgRelativeLayout.setBackgroundColor(Color.WHITE);
        runnable = new Runnable() {
            public void run() {
                changeBackground();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, lagDelay);
    }
}

