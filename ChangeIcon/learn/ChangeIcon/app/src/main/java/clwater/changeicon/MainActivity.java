package clwater.changeicon;


import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private ComponentName md;
    private ComponentName m1;
    private ComponentName m2;
    private PackageManager pm;
    Button btn1;
    Button btn2;
    Button btnd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        md = new ComponentName(getBaseContext() , "clwater.changeicon.MainActivity");

        m1 = new ComponentName(getBaseContext() , "clwater.changeicon.Test1");
        m2 = new ComponentName(getBaseContext() , "clwater.changeicon.Test2");
        pm = getApplicationContext().getPackageManager();
        btn1 = (Button) findViewById(R.id.change1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeIcon11(btn1);
            }
        });
        btn2 = (Button) findViewById(R.id.change2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeIcon12(btn2);
            }
        });
        btnd = (Button) findViewById(R.id.changed);
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this , "aa" , Toast.LENGTH_SHORT).show();
                changeIcond(btnd);
            }
        });


        
        saveFile();
    }

    private void saveFile() {
        Resources res = this.getResources();
        BitmapDrawable d = (BitmapDrawable) res.getDrawable(R.drawable.s11);
        Bitmap img = d.getBitmap();

        String fn = "ic.png";
        String path = this.getFilesDir() + File.separator + fn;
        Log.d("TAG" , path);
        try{
            OutputStream os = new FileOutputStream(path);
            img.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
        }catch(Exception e){
            Log.e("TAG", "", e);
        }
    }


    public void changeIcon11(View view) {
        disableComponent(md);
        disableComponent(m2);
        enableComponent(m1);
    }

    public void changeIcon12(View view) {
        disableComponent(md);
        disableComponent(m1);
        enableComponent(m2);
    }
    public void changeIcond(View view) {
        disableComponent(m1);
        disableComponent(m2);
        enableComponent(md);
    }

    private void enableComponent(ComponentName componentName) {
        pm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        pm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }


}
