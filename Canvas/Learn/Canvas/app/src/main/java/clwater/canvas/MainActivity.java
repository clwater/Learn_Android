package clwater.canvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

            Paint textPaint = new Paint();
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(30);
/**
 * 设置绘制文字时起始点X坐标的位置
 * CENTER:以文字的宽度的中心点为起始点向两边绘制
 * LEFT:以文字左边为起始点向右边开始绘制
 * RIGHT:以文字宽度的右边为起始点向左边绘制
 */
            textPaint.setTextAlign(Paint.Align.CENTER);

//获取文字度量信息
            Paint.FontMetrics fm = textPaint.getFontMetrics();
            float textHeight = fm.descent-fm.ascent;

//绘制文字的矩形框范围
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);


            String fn = "testQuick.png";
            String path = getContext().getFilesDir() + File.separator + fn;
            Bitmap bit = BitmapFactory.decodeFile(path);

            canvas.drawBitmap(bit , 0 , 0 ,null);

            canvas.drawCircle(100 , 100 , textHeight , paint);
            canvas.drawText("11", 100 ,100,  textPaint);





        }

    }
}
