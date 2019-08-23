package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MessageBox extends View {

    Paint paint;
    Path path;

    int width;
    int height;

    public MessageBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);


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
        canvas.translate(50f, 50f);

        path = new Path();

        /*
        path.lineTo(width-100, 0);
        path.lineTo(width-100, 100);
        path.lineTo(0, 100);
        path.lineTo(0, 0);
         */

        canvas.drawPath(path, paint);
    }
}
