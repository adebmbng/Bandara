package restart.com.bandara.presenters.signup;

import android.content.SharedPreferences;

import restart.com.bandara.services.APIService;

/**
 * Created by Debam on 2/19/17.
 */

public class SignupPresenters implements SignupPresenter{
    private SignupView view;
    private APIService api;

    public SignupPresenters(SignupView view, APIService api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void doValidate(String name, String email, String password) {
        view.onRegistering();
        if(validate(name, email, password)){
            doRegister();
        }
    }

    private boolean validate(String name, String email, String password){
        if (name.isEmpty() || name.length() < 3) {
            view.onValidateNameWrong();
            return false;
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.onValidateEmailWrong();
            return false;
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            view.onValidatePassWrong();
            return false;
        }
        return true;
    }

    @Override
    public void doRegister() {
        boolean register = true;
        if (register){
            view.onRegisterSuccess();
        } else{
            view.onRegisterFailed();
        }
    }

    @Override
    public void start() {

    }
}
