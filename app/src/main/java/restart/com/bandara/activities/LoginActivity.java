package restart.com.bandara.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import restart.com.bandara.MyApplication;
import restart.com.bandara.R;
import restart.com.bandara.presenters.login.LoginPresenter;
import restart.com.bandara.presenters.login.LoginPresenters;
import restart.com.bandara.presenters.login.LoginView;
import restart.com.bandara.services.APIService;
import restart.com.bandara.utils.Constant;
import restart.com.bandara.utils.ShrdPrfUtils;

/**
 * Created by lenovo on 2/7/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginView{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private Context ctx;
    private LoginPresenter presenters;
    private ProgressDialog progressDialog;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;

    @Inject
    SharedPreferences sp;

    @Inject
    APIService api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ctx = this;

        ((MyApplication) getApplication()).getDc().inject(this);
        setPresenter(presenters);

    }

    @OnClick(R.id.link_signup)
    void signUp(){
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
    }

    @OnClick(R.id.btn_login)
    void login() {
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if(!presenters.validate(email, password))
            return;

        presenters.doLogin();

    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    @Override
    public void onValidateEmailWrong() {
        _emailText.setError("enter a valid email address");
    }

    @Override
    public void onValidatePasswordWrong() {
        _passwordText.setError("between 4 and 10 alphanumeric characters");
    }

    @Override
    public void onValidationSuccess() {

    }

    @Override
    public void clearError() {
        _emailText.setError(null);
        _passwordText.setError(null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    @Override
    public void onLogin() {
        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        if(presenter != null){
            presenters = presenter;
        } else {
            presenters = new LoginPresenters(this, sp, api);
        }
    }
}
