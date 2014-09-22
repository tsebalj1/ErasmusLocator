package hr.mc2.worriesover.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by zubak on 1.5.2014..
 */
public class SplachScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 1500;
    private TextView text1;
    private TextView text2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splach_screen);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);
        Typeface type1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto-Light.ttf");
        text1.setTypeface(type1);
        text2.setTypeface(type1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplachScreen.this, MainActivity.class);
                SplachScreen.this.startActivity(mainIntent);
                SplachScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);

    }

}