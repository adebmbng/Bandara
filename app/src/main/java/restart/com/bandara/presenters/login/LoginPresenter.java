package restart.com.bandara.presenters.login;

import restart.com.bandara.BasePresenter;

/**
 * Created by Debam on 2/19/17.
 */

public interface LoginPresenter extends BasePresenter {
    boolean validate(String username, String pass);
    void doLogin();
}
