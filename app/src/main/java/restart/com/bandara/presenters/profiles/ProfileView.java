package restart.com.bandara.presenters.profiles;

import restart.com.bandara.BaseView;
import restart.com.bandara.models.Profile;

/**
 * Created by Debam on 2/22/17.
 */

public interface ProfileView extends BaseView<ProfilePresenter> {
    void setProfile(Profile p);
    void onFailed(String msg);
}
