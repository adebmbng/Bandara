package restart.com.bandara.presenters.signup;

import restart.com.bandara.BaseView;

/**
 * Created by Debam on 2/19/17.
 */

public interface SignupView extends BaseView<SignupPresenter>{
    void onRegistering();
    void onRegisterSuccess();
    void onRegisterFailed();
    void onValidateNameWrong();
    void onValidateEmailWrong();
    void onValidatePassWrong();
    void clearError();
}
