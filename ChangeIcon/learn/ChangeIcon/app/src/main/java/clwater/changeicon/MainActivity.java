package clwater.changeicon;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        showImage();
        createQuick();



    }

    private void showImage() {
        String fn = "testQuick.png";
        String path = this.getFilesDir() + File.separator + fn;
        ImageView iv = (ImageView) findViewById(R.id.image);
        Bitmap bit = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bit);
    }

    private void createQuick() {
        Intent shortcutintent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutintent.putExtra("duplicate", false);
        // 需要现实的名称
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                "TestQuck");
        String fn = "testQuick.png";
        String path = this.getFilesDir() + File.separator + fn;
        Bitmap bit = BitmapFactory.decodeFile(path);

        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bit);

        // 点击快捷图片，运行的程序主入口
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
                new Intent(this.getApplicationContext(), this.getClass()));
        // 发送广播
        this.sendBroadcast(shortcutintent);
    }

    private void saveFile() {
        Resources res = this.getResources();
        BitmapDrawable d = (BitmapDrawable) res.getDrawable(R.drawable.testmmm);
        Bitmap img = d.getBitmap();

        String fn = "testQuick.png";
        String path = this.getFilesDir() + File.separator + fn;
        try{
            OutputStream os = new FileOutputStream(path);
            img.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
        }catch(Exception e){
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
