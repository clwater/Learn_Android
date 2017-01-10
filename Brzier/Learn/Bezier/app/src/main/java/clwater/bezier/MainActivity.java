package clwater.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Cu(this));
    }

    class Cu extends View{

        public Cu(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint mPaint = new Paint();
            mPaint.setColor(Color.RED); //画笔颜色
            mPaint.setStrokeWidth(10); //画笔宽度
            //mPaint.setStyle(Paint.Style.STROKE);

//            Path mPath = new Path();
//            mPath.reset();
//            //起点
//
//            mPath.moveTo(100, 100);
//            mPath.cubicTo(800, 100, 100, 800, 800, 800);
//            // 一共四个点，(100, 100)和(800, 800)分别为起点和终点,(800, 100)和(100, 800)为操作点
//            canvas.drawPath(mPath, mPaint);

            float leftCircleX = 100 ,mHeight = 200 , rightCircleRadius = 80 , leftCircleRadius = 50 , rightCircleX = 400;
            canvas.drawCircle(leftCircleX, mHeight / 2  , leftCircleRadius, mPaint);
            mPaint.setColor(Color.GREEN);
            canvas.drawCircle(rightCircleX, mHeight / 2 , rightCircleRadius, mPaint);


            Path mPath = new Path();


            //float middleOffset = (leftCircleX - rightCircleX) / (mPoints.get(1).x - mPoints.get(0).x) * (mHeight / 10);
            float middleOffset = (leftCircleX - rightCircleX) / (400 - 100 ) * (mHeight / 10);
            mPath.reset();
            mPath.moveTo(rightCircleX, mHeight / 2);
            mPath.lineTo(rightCircleX, mHeight / 2 - rightCircleRadius);
            mPath.cubicTo(rightCircleX,
                    mHeight / 2 - rightCircleRadius,
                    rightCircleX + (leftCircleX - rightCircleX) / 2.0F,
                    mHeight / 2 + middleOffset,
                    leftCircleX,
                    mHeight / 2 - leftCircleRadius);
            mPath.lineTo(leftCircleX, mHeight / 2 + leftCircleRadius);
            mPath.cubicTo(leftCircleX,
                    mHeight / 2 + leftCircleRadius,
                    rightCircleX + (leftCircleX - rightCircleX) / 2.0F,
                    mHeight / 2 - middleOffset,
                    rightCircleX,
                    mHeight / 2 + rightCircleRadius);
            mPath.close();
            canvas.drawPath(mPath, mPaint);


        }
    }

    class Index{
        float x ,y;
        public Index(float x , float y){
            this.x = x;
            this.y = y;
        }
    }
}
