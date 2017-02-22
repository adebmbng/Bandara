package restart.com.bandara;

import javax.inject.Singleton;

import dagger.Component;
import restart.com.bandara.activities.LoginActivity;
import restart.com.bandara.activities.MainActivity;
import restart.com.bandara.activities.MapsActivity;
import restart.com.bandara.activities.ProfileActivity;
import restart.com.bandara.activities.SignupActivity;
import restart.com.bandara.activities.SplashActivity;
import restart.com.bandara.presenters.login.LoginPresenters;

/**
 * Created by Debam on 2/19/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface DIComponent {
    void inject(LoginActivity act);
    void inject(SignupActivity act);
    void inject(MainActivity act);
    void inject(SplashActivity act);
    void inject(ProfileActivity act);
    void inject(MapsActivity act);
}
