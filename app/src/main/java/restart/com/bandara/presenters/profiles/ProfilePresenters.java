package restart.com.bandara.presenters.profiles;

import android.content.SharedPreferences;

import restart.com.bandara.models.Profile;
import restart.com.bandara.presenters.promo.PromoView;
import restart.com.bandara.services.APIService;

/**
 * Created by Debam on 2/22/17.
 */

public class ProfilePresenters implements ProfilePresenter {

    private ProfileView view;
    private APIService api;
    private SharedPreferences sp;

    public ProfilePresenters(ProfileView view, APIService api, SharedPreferences sp) {
        this.view = view;
        this.api = api;
        this.sp = sp;
        view.setPresenter(this);
    }

    @Override
    public void getProfile() {
        //hit API
        Profile p = new Profile();
        p.setEmail("");
        p.setName("Jivan Bule");
        p.setMsisd("0811111111");
        p.setImgUrl("");

        view.setProfile(p);
    }
}
