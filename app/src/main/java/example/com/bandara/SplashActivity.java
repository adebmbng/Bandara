package example.com.bandara;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by lenovo on 2/7/2017.
 */

public class SplashActivity extends Activity {
    private static int interval = 3000;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            }
        },interval);
    }
}
