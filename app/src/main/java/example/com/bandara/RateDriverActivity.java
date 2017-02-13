package example.com.bandara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by lenovo on 2/7/2017.
 */

public class RateDriverActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ratedriver);

        Button buttonSearchDriver = (Button) findViewById(R.id.btn_done);
        buttonSearchDriver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RateDriverActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
