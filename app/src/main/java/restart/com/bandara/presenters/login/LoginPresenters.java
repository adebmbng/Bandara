package restart.com.bandara.presenters.login;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import restart.com.bandara.MyApplication;
import restart.com.bandara.services.APIService;
import restart.com.bandara.utils.Constant;
import restart.com.bandara.utils.ShrdPrfUtils;

/**
 * Created by Debam on 2/19/17.
 */

public class LoginPresenters implements LoginPresenter {

    private LoginView view;

    private SharedPreferences sp;

    private APIService api;

    public LoginPresenters(LoginView view, SharedPreferences sp, APIService api) {
        this.view = view;
        this.sp = sp;
        this.api = api;
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
            ShrdPrfUtils.saveBoolean(sp, Constant.SHARED_PREFERENCE.HAS_LOGIN, true);
            view.onLoginSuccess();
        } else {
            view.onLoginFailed();
        }
    }

    @Override
    public void start() {

    }
}
