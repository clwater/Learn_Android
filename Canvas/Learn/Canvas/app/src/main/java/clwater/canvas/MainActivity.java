package clwater.canvas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static android.R.attr.path;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        
        image();
        setContentView(new CustomView1(this));

        createBit();
    }

    private void image() {
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

    private void createBit() {
        Bitmap bm = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint p = new Paint();
        canvas.drawRect(50, 50, 200, 200, p);
        canvas.save(Canvas.ALL_SAVE_FLAG );
        canvas.restore();

        File f = new File("/sdcard/0.png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 50, fos);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getBaseContext().getContentResolver(),
                    f.getAbsolutePath(), "testX", null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新

    }


    /**
     * 使用内部类 自定义一个简单的View
     * @author Administrator
     *
     */
    class CustomView1 extends View {


        public CustomView1(Context context) {
            super(context);

        }

        //在这里我们将测试canvas提供的绘制图形方法
        @Override
        protected void onDraw(Canvas canvas) {

            Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);


            String fn = "testQuick.png";
            String path = getContext().getFilesDir() + File.separator + fn;
            Bitmap bit = BitmapFactory.decodeFile(path);


            float _height = bit.getHeight();
            float _width = bit.getWidth();

            float bHeight = _height / 8 ;
            float bWidth = _width / 8;



            Typeface font = Typeface.create("宋体", Typeface.BOLD);

            Paint textPaint = new Paint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize((float) (_height * 0.25));
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTypeface(font);

            Paint.FontMetrics fm = textPaint.getFontMetrics();
            float textHeight = fm.descent-fm.ascent;

            float textY =  bHeight  + (fm.descent - fm.ascent) / 2 - fm.descent;

            Paint paint = new Paint();
            paint.setColor(Color.RED);

            canvas.drawBitmap(bit , 200 ,  200  ,null);

           // canvas.drawCircle(200 + _width  , 200 +bHeight - bHeight / 2 , textHeight / 4 * 3  , paint);


            String  num = "6";

            int numLengh = num.length() - 1;

            if (numLengh >= 4){

                num = num.substring(0 ,1) + "..." + num.substring(num.length() - 3 , num.length());

                numLengh = 4;
            }



            float _size = (float) 1.3;
            float _dsize = textHeight * _size;
            float _dJsize = _dsize / 3 * numLengh;

            RectF rectL = new RectF(0 , 0, _dsize, _dsize);
            RectF rectR = new RectF(0 , 0, _dsize, _dsize);
            rectL.offset(200 + _width  - _dsize / 2  - _dJsize + 1 , 200 - _dsize / 2);
            rectR.offset(200 + _width  - _dsize / 2  , 200 - _dsize / 2);
            canvas.drawArc(rectL, 90, 180, true, paint);
            paint.setColor(Color.RED);
            canvas.drawArc(rectR, -90, 180, true, paint);

            paint.setColor(Color.RED);
            RectF r = new RectF(0 , 0 , _dJsize , _dsize + 1);
            r.offset(200 + _width  -  _dJsize , 200 - _dsize / 2);
            canvas.drawRect(r ,  paint);



            canvas.drawText(num , 200 + _width - _dJsize / 2 , 200 + textY - bHeight,  textPaint);


        }

    }
}
