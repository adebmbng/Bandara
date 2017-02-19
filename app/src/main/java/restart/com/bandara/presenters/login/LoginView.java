package restart.com.bandara.presenters.login;

import restart.com.bandara.BaseView;

/**
 * Created by Debam on 2/19/17.
 */

public interface LoginView extends BaseView<LoginPresenter> {
    void onValidateEmailWrong();
    void onValidatePasswordWrong();
    void onValidationSuccess();
    void clearError();
    void showLoading();
    void onLoginSuccess();
    void onLoginFailed();
    void onLogin();
}
