package restart.com.bandara.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import java.util.List;

import javax.inject.Inject;

import restart.com.bandara.MyApplication;
import restart.com.bandara.R;
import restart.com.bandara.utils.Constant;
import restart.com.bandara.utils.FileUtil;
import restart.com.bandara.utils.ShrdPrfUtils;

/**
 * Created by lenovo on 2/7/2017.
 */

public class SplashActivity extends Activity {
    private static int interval = 3000;

    @Inject
    SharedPreferences sp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);
        ((MyApplication) getApplication()).getDc().inject(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (ShrdPrfUtils.getBoolean(sp, Constant.SHARED_PREFERENCE.HAS_LOGIN)) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, interval);
    }
}
