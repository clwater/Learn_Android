package clwater.canvas;

import android.content.Context;
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        
        image();
        setContentView(new CustomView1(this));

        //createBit();
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
        Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

    }


    /**
     * 使用内部类 自定义一个简单的View
     * @author Administrator
     *
     */
    class CustomView1 extends View {

        Paint paint;

        public CustomView1(Context context) {
            super(context);
            paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.YELLOW);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(1);
        }

        //在这里我们将测试canvas提供的绘制图形方法
        @Override
        protected void onDraw(Canvas canvas) {
            //canvas.drawCircle(100, 100, 90, paint);

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




            float _size = (float) 1.3;
            float _dsize = textHeight * _size;
            RectF rect = new RectF(0 , 0, _dsize, _dsize);
            rect.offset(200 + _width  - _dsize / 2 , 200 - _dsize / 2);
            canvas.drawArc(rect, 90, 180, true, paint);
            canvas.drawArc(rect, -90, 180, true, paint);


            String  num = "6";
            canvas.drawText(num , 200 + _width  , 200 + textY - bHeight,  textPaint);

            Log.d("gzb"  , "" + num.length());
        }

    }
}
