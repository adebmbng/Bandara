package restart.com.bandara.utils;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Debam on 2/19/17.
 */

public class ShrdPrfUtils {

    private static final String TAG = "SharedPreferrence";

    public static void saveString(SharedPreferences sp, String key, String values){
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key,values);
        ed.commit();
        Log.d(TAG, key+" commited");
        
    }

    public static void saveBoolean(SharedPreferences sp, String key, boolean b) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(key,b);
        ed.commit();
        Log.d(TAG, key+" commited");
    }

    public static boolean getBoolean(SharedPreferences sp, String key){
        return sp.getBoolean(key, false);
    }
}
