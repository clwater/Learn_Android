package clwater.nightmode;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by gengzhibo on 17/1/3.
 */

public class App  extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}
