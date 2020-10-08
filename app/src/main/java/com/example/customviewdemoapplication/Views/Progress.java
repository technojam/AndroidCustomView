package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customviewdemoapplication.R;

public class Progress extends View {

    float mCircleX;
    float mCircleY;
    float sweepAngle = 180;

    int mTextSize;

    private int mTextColor;
    private int mProgressColor;

    private static final int DEFAULT_TEXT_SIZE = 50;
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_PROGRESS_COLOR = Color.RED;

    public Progress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Progress);

        mTextSize = typedArray.getDimensionPixelSize(R.styleable.Progress_text_size, DEFAULT_TEXT_SIZE);

        mTextColor = typedArray.getColor(R.styleable.Progress_text_color, DEFAULT_TEXT_COLOR);
        mProgressColor = typedArray.getColor(R.styleable.Progress_progress_color, DEFAULT_PROGRESS_COLOR);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCircleX = getWidth() / 2.f;
        mCircleY = getWidth() / 2.f;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(50);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);

        canvas.drawCircle(mCircleX, mCircleY, getWidth() / 3.f,  paint);

        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setStrokeWidth(50);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(mProgressColor);
        paint1.setStrokeCap(Paint.Cap.ROUND);

        RectF rect = new RectF();
        rect.top = mCircleX - getWidth() / 3.f;
        rect.left = mCircleX - getWidth() / 3.f;
        rect.bottom = mCircleY + getWidth() / 3.f;
        rect.right = mCircleY + getWidth() / 3.f;

        canvas.drawArc(rect, 270, sweepAngle, false, paint1);

        Paint paint2 = new Paint();
        paint2.setColor(mTextColor);
        paint2.setTextSize(mTextSize);
        paint2.setTextAlign(Paint.Align.CENTER);

        canvas.drawText("75", mCircleX,  mCircleY+20, paint2);
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
    }
    */
}
