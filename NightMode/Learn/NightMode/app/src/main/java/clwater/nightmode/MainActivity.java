package clwater.nightmode;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

//        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//
//        if(mode == Configuration.UI_MODE_NIGHT_YES) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        } else if(mode == Configuration.UI_MODE_NIGHT_NO) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else {
//            // blah blah
//        }
//
//        recreate();



           Button light =  (Button) findViewById(R.id.light);
           light.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                  // getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                   recreate();
               }
           });

            Button night =  (Button) findViewById(R.id.night);
            night.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                 //   getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                    recreate();
                }
            });



    }
}
