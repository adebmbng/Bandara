package restart.com.bandara.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import restart.com.bandara.presenters.signup.SignupPresenter;
import restart.com.bandara.presenters.signup.SignupPresenters;
import restart.com.bandara.presenters.signup.SignupView;
import restart.com.bandara.services.APIService;

/**
 * Created by lenovo on 2/7/2017.
 */

public class SignupActivity extends AppCompatActivity implements SignupView{
    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;

    private Context ctx;
    private SignupPresenter presenters;
    private ProgressDialog progressDialog;

    @Inject
    APIService api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getDc().inject(this);
        ctx = this;
        setPresenter(presenters);

    }

    @OnClick(R.id.link_login)
    void gotoLogin(){
        finish();
    }

    @OnClick(R.id.btn_signup)
    public void signup() {
        Log.d(TAG, "Signup");

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        presenters.doValidate(name, email, password);

    }

    @Override
    public void onRegistering() {
        clearError();
        _signupButton.setEnabled(false);

        progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
    }

    @Override
    public void onRegisterSuccess() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        _signupButton.setEnabled(true);
                        setResult(RESULT_OK, null);
                        finish();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    public void onRegisterFailed() {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Sign Up failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    @Override
    public void onValidateNameWrong() {
        progressDialog.dismiss();
        _nameText.setError("at least 3 characters");
    }

    @Override
    public void onValidateEmailWrong() {
        progressDialog.dismiss();
        _emailText.setError("enter a valid email address");
    }

    @Override
    public void onValidatePassWrong() {
        progressDialog.dismiss();
        _passwordText.setError("between 4 and 10 alphanumeric characters");
    }

    @Override
    public void clearError() {
        _nameText.setError(null);
        _emailText.setError(null);
        _passwordText.setError(null);
    }

    @Override
    public void setPresenter(SignupPresenter presenter) {
        if(presenter!=null){
            presenters = presenter;
        } else {
            presenters = new SignupPresenters(this, api);
        }
    }
}
