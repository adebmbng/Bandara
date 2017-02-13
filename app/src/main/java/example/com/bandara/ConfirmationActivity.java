package example.com.bandara;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2/7/2017.
 */

public class ConfirmationActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_confirmation);

        Button buttonSearchDriver = (Button) findViewById(R.id.btn_findDriver);
        buttonSearchDriver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(ConfirmationActivity.this, SearchDriverActivity.class);
//                startActivity(i);
                findDriver();
            }
        });
    }

    public void findDriver() {

        final ProgressDialog progressDialog = new ProgressDialog(ConfirmationActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Find Your Driver .....");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        Intent intent = new Intent(ConfirmationActivity.this, SearchDriverActivity.class);
                        startActivity(intent);
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
}
