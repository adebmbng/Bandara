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

public class SearchDriverActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_searchdriver);

        Button buttonOkDriver = (Button) findViewById(R.id.btn_okDriver);
        buttonOkDriver.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchDriverActivity.this, RateDriverActivity.class);
                startActivity(i);
            }
        });
    }
}
