package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import com.example.customviewdemoapplication.R;

import java.util.HashMap;

public class RoomTypeView extends View {

    static float ADD_RIGHT_MARGIN = 300f;
    static float ADD_SUBTRACT_COUNTER_GAP = 80f;

    static int TOUCH_AREA = 30;


    String mText;
    float mLeftGap, mTextSize, mTextLeftGap, mNumberPickerRightGap;
    int mCounter = 0;
    int mTextColor, mCounterTextColor;



    float mCircleX, mCircleY;
    float mCircleRadius = 10f;
    Paint mPaintCircle;
    Paint mPaintText;
    Paint mPaintCounterText;

    HashMap<String, Float> mPosition = new HashMap<>();




    public RoomTypeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.RoomTypeView);

        mText = typedArray.getString(R.styleable.RoomTypeView_text);
        mTextSize = typedArray.getDimension(R.styleable.RoomTypeView_textSize, 100);
        mLeftGap = typedArray.getDimension(R.styleable.RoomTypeView_leftGap, 18);
        mTextLeftGap = typedArray.getDimension(R.styleable.RoomTypeView_textLeftGap, 20);
        mNumberPickerRightGap = typedArray.getDimension(R.styleable.RoomTypeView_numberPickerRightGap, 50);

        mTextColor = typedArray.getInt(R.styleable.RoomTypeView_textColor, Color.BLACK);
        mCounterTextColor = typedArray.getInt(R.styleable.RoomTypeView_counterTextColor, Color.BLACK);

        typedArray.recycle();

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.BLACK);

        mPaintText = new Paint();
        mPaintText.setColor(mTextColor);
        mPaintText.setTextSize(mTextSize);

        mPaintCounterText = new Paint();
        mPaintCounterText.setColor(mCounterTextColor);
        mPaintCounterText.setTextSize(mTextSize);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCircleX = mLeftGap;
        mCircleY = getHeight()/2;

        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);

        String a = "ᴧ, v";

        canvas.drawText(mText, mLeftGap+mTextLeftGap, getHeight()/2 + mTextSize/2 - mCircleRadius, mPaintText);

        canvas.drawText("<", getWidth()-ADD_RIGHT_MARGIN- mNumberPickerRightGap, getHeight()/2 + mTextSize/2 - mCircleRadius, mPaintCounterText);

        canvas.drawText(""+mCounter, getWidth()-ADD_RIGHT_MARGIN+ADD_SUBTRACT_COUNTER_GAP- mNumberPickerRightGap, getHeight()/2 + mTextSize/2 - mCircleRadius, mPaintCounterText);

        canvas.drawText(">", getWidth()-ADD_RIGHT_MARGIN+2*ADD_SUBTRACT_COUNTER_GAP- mNumberPickerRightGap, getHeight()/2 + mTextSize/2 - mCircleRadius, mPaintCounterText);

        mPosition.put("DECREMENT_X", getWidth()-ADD_RIGHT_MARGIN- mNumberPickerRightGap);
        mPosition.put("INCREMENT_X", getWidth()-ADD_RIGHT_MARGIN+2*ADD_SUBTRACT_COUNTER_GAP- mNumberPickerRightGap);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        float x = event.getX();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                if(x >= mPosition.get("DECREMENT_X")-TOUCH_AREA && x <= mPosition.get("DECREMENT_X")+TOUCH_AREA){
                    if(mCounter > 0){
                        mCounter -= 1;
                        postInvalidate();
                    }
                }

                if(x >= mPosition.get("INCREMENT_X")-TOUCH_AREA && x <= mPosition.get("INCREMENT_X")+TOUCH_AREA){
                    if(mCounter < 9){
                        mCounter += 1;
                        postInvalidate();
                    }
                }
                break;

        }

        return value;
    }

    
}
