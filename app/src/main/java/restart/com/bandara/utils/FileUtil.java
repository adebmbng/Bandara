package restart.com.bandara.utils;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * Created by Debam on 2/19/17.
 */

public class FileUtil {
    public static List<String> getClasses(Context context, String packageName){
        ArrayList<String> list = new ArrayList<>();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements();) {
                String s = iter.nextElement();
                if(s != null && s.length() >= packageName.length() && s.subSequence(0, packageName.length()).equals(packageName)){
                    list.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
