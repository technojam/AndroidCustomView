package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Progress extends View {

    float mCircleX;
    float mCircleY;
    float sweepAngle = 180;

    public Progress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCircleX = getWidth()/2;
        mCircleY = getWidth()/2;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(50);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);

        canvas.drawCircle(mCircleX, mCircleY, getWidth()/3,  paint);

        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setStrokeWidth(50);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.RED);
        paint1.setStrokeCap(Paint.Cap.ROUND);

        RectF rect = new RectF();
        rect.top = mCircleX - getWidth()/3;
        rect.left = mCircleX - getWidth()/3;
        rect.bottom = mCircleY + getWidth()/3;
        rect.right = mCircleY + getWidth()/3;

        //canvas.drawRect(rect, paint1);

        canvas.drawArc(rect, 270, sweepAngle, false, paint1);

        int correction = 200;
        Paint paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setTextSize(50);
        paint2.setTextAlign(Paint.Align.CENTER);



        canvas.drawText("75", mCircleX,  mCircleY+10, paint2);


    }

    /*

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
                float x = getX();
                float y = getY();

                double dx = Math.pow(x - mCircleX, 2);
                double dy = Math.pow(y - mCircleY, 2);

                if(dy + dx < Math.pow(getWidth()/3, 2)+100 & dy + dx > Math.pow(getWidth()/3, 2)-100){
                    Toast.makeText(getContext(), "Click inside", Toast.LENGTH_SHORT).show();

                    sweepAngle += 10;

                    postInvalidate();
                    return true;

                }
                return value;

        }
        return value;
    }*/

}
