package restart.com.bandara.presenters.login;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import restart.com.bandara.MyApplication;
import restart.com.bandara.services.APIService;

/**
 * Created by Debam on 2/19/17.
 */

public class LoginPresenters implements LoginPresenter {

    private LoginView view;
    private Context ctx;

    @Inject
    SharedPreferences sp;

    @Inject
    APIService api;

    public LoginPresenters(LoginView view, Context ctx) {
        this.view = view;
        this.ctx = ctx;
        ((MyApplication) ctx).getDc().inject(this);
    }

    @Override
    public boolean validate(String email, String password) {

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.onValidateEmailWrong();
            return false;
        } else {
            view.clearError();
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            view.onValidatePasswordWrong();
            return false;
        } else {
            view.clearError();
        }

        return true;
    }

    @Override
    public void doLogin() {
        view.onLogin();
        boolean login=true;

        if(login){
            view.onLoginSuccess();
        } else {
            view.onLoginFailed();
        }
    }

    @Override
    public void start() {

    }
}
