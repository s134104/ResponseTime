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

public class MainActivity extends AppCompatActivity {

    private RelativeLayout bgRelativeLayout;
    private Button random_button, lag_button;
    private TextView responseTime;

    android.os.Handler handler = new android.os.Handler();
    Runnable runnable;
    long lagDelay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        random_button = (Button) findViewById(R.id.random);
        lag_button= (Button) findViewById(R.id.lag);



    }

    public void random(View view) {

    startActivity(new Intent(this, RandomTest.class));
    }

    public void lag(View view) {
        startActivity(new Intent(this, HighLagTest.class));


    }
    }

