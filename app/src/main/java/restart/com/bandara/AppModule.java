package restart.com.bandara;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import restart.com.bandara.services.APIService;
import restart.com.bandara.utils.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Debam on 2/19/17.
 */
@Module
public class AppModule {

    private Application app;
    private APIService api;

    public AppModule(Application app) {
        this.app = app;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        Retrofit base = new Retrofit.Builder()
                .baseUrl(Constant.URL_CONSTANT.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        api = base.create(APIService.class);
    }

    @Provides
    @Singleton
    Context provideContext(){
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSP(){
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    APIService provideAPI(){
        return api;
    }
}
