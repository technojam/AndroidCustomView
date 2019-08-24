package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MessageBox extends View {

    Paint paint;
    Path path;

    Paint textPaint;

    int width;
    int height;

    public MessageBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(40);
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMinimumHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(20f, 50f);

        path = new Path();

        /*
        path.lineTo(width-100, 0);
        path.lineTo(width-100, 100);
        path.lineTo(0, 100);
        path.lineTo(0, 0);
         */

        /*
        path.addArc(0f, 0f, 0+100, 0+100, 180, 90);
        path.lineTo(width-100, 0);
        path.addArc(width-100-50, 0f, width-100+50, 100, 270, 90);
        path.lineTo(width-100+50, 200);
        path.addArc(width-100-50, 200-50, width-100+50, 50+200, 0, 90);
        path.lineTo(50, 200+50);
        path.addArc(0, 200-50, 100, 200+50, 90, 90);
        path.lineTo(0, 50);

         */

        int w = width-200;
        int h = 100;

        path.addArc(0f, 0f, 0+100, 0+100, 180, 90);
        path.lineTo(w, 0);

        path.addArc(w-50, 0f, w+50, 100, 270, 90);
        path.lineTo(w+50, h);

        path.addArc(w+50, h-50, w+50+50, 50+h, 90, 90);
        path.moveTo(w+70, h+50);
        path.lineTo(50, h+50);


        path.addArc(0, h-50, 100, h+50, 90, 90);
        path.lineTo(0, 50);

        canvas.drawPath(path, paint);
        canvas.drawText("Hello", 50, 50, textPaint);
    }
}
