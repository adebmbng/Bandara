package restart.com.bandara.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import restart.com.bandara.R;

/**
 * Created by lenovo on 2/17/2017.
 */

public class EditProfileActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_editprofile);
    }
}
