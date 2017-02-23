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
    private Button click, slow, fast, perfect;
    private TextView responseTime;

    android.os.Handler handler = new android.os.Handler();
    Runnable runnable;
    long lagDelay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        click = (Button) findViewById(R.id.button7);
        slow = (Button) findViewById(R.id.button);
        fast = (Button) findViewById(R.id.button3);
        perfect = (Button) findViewById(R.id.button2);
        responseTime = (TextView) findViewById(R.id.textView4);

        // Disable buttons (wait till click)
        slow.setEnabled(false);
        fast.setEnabled(false);
        perfect.setEnabled(false);

    }

    public void changeBackground() {
        bgRelativeLayout.setBackgroundColor(Color.GRAY);
        click.setEnabled(false);
        slow.setEnabled(true);
        fast.setEnabled(true);
        perfect.setEnabled(true);

        responseTime.setVisibility(View.VISIBLE);
        responseTime.setText("Did you experience lag?");
    }

    public void tooFast(View view) {
        click.setEnabled(true);
        slow.setEnabled(false);
        fast.setEnabled(false);
        perfect.setEnabled(false);
        responseTime.setVisibility(View.INVISIBLE);

        lagDelay += 100;
        click(view);
    }

    public void tooSlow(View view) {
        click.setEnabled(true);
        slow.setEnabled(false);
        fast.setEnabled(false);
        perfect.setEnabled(false);
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

        click.setVisibility(View.VISIBLE);
        click.setText("↺ RESTART");
        click.setEnabled(true);
        slow.setEnabled(false);
        fast.setEnabled(false);
        perfect.setEnabled(false);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }

    public void click(View view) {
        click.setVisibility(View.INVISIBLE);

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
