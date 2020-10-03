package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customviewdemoapplication.R;

public class MessageBox extends View {

    Paint paint;
    Path path;

    Paint textPaint;

    int width;
    int height;

    String text;
    float strokeWidth;
    int textColor;
    int strokeColor;

    public MessageBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MessageBox);
        text = typedArray.getString(R.styleable.MessageBox_text);
        strokeWidth = typedArray.getDimension(R.styleable.MessageBox_android_strokeWidth, 5f);
        textColor = typedArray.getColor(R.styleable.MessageBox_textColor, Color.GREEN);
        strokeColor = typedArray.getColor(R.styleable.MessageBox_android_strokeColor, Color.RED);
        typedArray.recycle();
        init(attrs);
    }

    private void init(AttributeSet attrs){
        paint = new Paint();
        paint.setColor(strokeColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(textColor);
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
        canvas.drawText(text, 50, 50, textPaint);
    }
}
