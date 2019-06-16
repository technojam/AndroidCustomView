package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewdemoapplication.R;

public class RoomTypeView extends View {

    String mText;
    float mLeftGap, mTextSize, mTextLeftGap;
    int mCounter = 0;

    float mCircleX, mCircleY;
    float mCircleRadius = 10f;
    Paint mPaintCircle;
    Paint mPaintText;




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

        typedArray.recycle();



        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.BLACK);

        mPaintText = new Paint();
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(mTextSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCircleX = mLeftGap;
        mCircleY = getHeight()/2;

        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);

        canvas.drawText(mText, mLeftGap+mTextLeftGap, getHeight()/2 + 2*mCircleRadius, mPaintText);

    }
}
