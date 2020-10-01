package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class CustomAnalogClock extends View {
    //height and width of the clock view
    private int mHeight,mWidth = 0;
    // numeric numbers to denote the hours
    private int[] mClockHours  = {1,2,3,4,5,6,7,8,9,10,11,12};
    // padding of the clock-hands around the clock
    private int mPadding = 0;
    private int mNumericalSpacing = 0;
    // heights of the clock hands
    private int mHandTruncation, mHourHandTruncation = 0;

    private int mRadius = 0;
    private Paint mPaint;
    private Rect mRect = new Rect();
    private boolean isInit; // it will be true once clock is initalized


    public CustomAnalogClock(Context context) {
        super(context);
    }

    public CustomAnalogClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAnalogClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomAnalogClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // implement of the analog clock ui component
        if(!isInit){
            mPaint = new Paint();
            mHeight = getHeight();
            mWidth = getWidth();
            mPadding = mNumericalSpacing + 50; // spacing from the circle border
            int minAttr = Math.min(mHeight,mWidth);
            mRadius = minAttr / 2 -mPadding;
             // different heights
            mHandTruncation = minAttr /20;
            mHourHandTruncation = minAttr / 17;
            isInit = true;


        }

        canvas.drawColor(Color.DKGRAY);

        mPaint.reset();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius + mPadding - 10, mPaint);

        // center
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 12, mPaint);

        int fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        mPaint.setTextSize(fontSize);  // set font size

        for (int hour : mClockHours) {
            String tmp = String.valueOf(hour);
            mPaint.getTextBounds(tmp, 0, tmp.length(), mRect);  // for circle-wise bounding

            // find the circle-wise (x, y) position as mathematical rule
            double angle = Math.PI / 6 * (hour - 3);
            int x = (int) (mWidth / 2 + Math.cos(angle) * mRadius - mRect.width() / 2);
            int y = (int) (mHeight / 2 + Math.sin(angle) * mRadius + mRect.height() / 2);

            canvas.drawText(String.valueOf(hour), x, y, mPaint);  // you can draw dots to denote hours as alternative


        }
        Calendar calendar = Calendar.getInstance();
        float hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;

        drawHandLine(canvas, (hour + calendar.get(Calendar.MINUTE) / 60) * 5f, true, false); // draw hours
        drawHandLine(canvas, calendar.get(Calendar.MINUTE), false, false); // draw minutes
        drawHandLine(canvas, calendar.get(Calendar.SECOND), false, true); // draw seconds

        // invalidate the appearance for next representation of time
        postInvalidateDelayed(500);
        invalidate();
    }
    private void drawHandLine(Canvas canvas, double moment, boolean isHour, boolean isSecond) {
        double angle = Math.PI * moment / 30 - Math.PI / 2;
        int handRadius = isHour ? mRadius - mHandTruncation - mHourHandTruncation : mRadius - mHandTruncation;
        if (isSecond) mPaint.setColor(Color.YELLOW);
        canvas.drawLine(mWidth / 2, mHeight / 2, (float) (mWidth / 2 + Math.cos(angle) * handRadius), (float) (mHeight / 2 + Math.sin(angle) * handRadius), mPaint);
    }

}



