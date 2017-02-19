package restart.com.bandara.presenters.signup;

import restart.com.bandara.BasePresenter;

/**
 * Created by Debam on 2/19/17.
 */

public interface SignupPresenter extends BasePresenter {
    void doValidate(String name, String email, String password);
    void doRegister();
}
