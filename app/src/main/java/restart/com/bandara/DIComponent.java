package restart.com.bandara;

import javax.inject.Singleton;

import dagger.Component;
import restart.com.bandara.activities.LoginActivity;

/**
 * Created by Debam on 2/19/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface DIComponent {
    void inject(LoginActivity act);
}
