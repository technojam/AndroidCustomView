package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.example.customviewdemoapplication.R;

public class ReviewBarView extends View {


    int mBgColor, mFgColor;
    float mPercentage, mTextSize;

    Paint mPaintBar;
    Paint mPaintProgress;


    Rect mRectBar;
    Rect mRectProgress;


    public ReviewBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    private void init(AttributeSet set){
        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.ReviewBarView);

        mBgColor = typedArray.getInteger(R.styleable.ReviewBarView_bgColor, R.color.grey);
        mFgColor = typedArray.getInteger(R.styleable.ReviewBarView_fgColor, R.color.colorPrimary);
        mPercentage = typedArray.getFloat(R.styleable.ReviewBarView_percentage, 50f);

        typedArray.recycle();

        mRectBar = new Rect();
        mRectProgress = new Rect();

        mPaintBar = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);


        mPaintBar.setColor(mBgColor);
        mPaintProgress.setColor(mFgColor);





    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectBar.top = 0;
        mRectBar.left = 0;
        mRectBar.bottom = mRectBar.top + getHeight();
        mRectBar.right = mRectBar.left + getWidth();


        mRectProgress.top = 0;
        mRectProgress.left = 0;
        mRectProgress.bottom = mRectProgress.top + getHeight();
        mRectProgress.right = mRectProgress.left + getProgressWidth();


        canvas.drawRect(mRectBar, mPaintBar);
        canvas.drawRect(mRectProgress, mPaintProgress);

    }

    private int getProgressWidth(){

        int progressWidth;

        if (mPercentage<=100 && mPercentage>=0){
            progressWidth = (int)(mRectBar.width()*mPercentage/100);
        }else{
            progressWidth = 0;
        }

        return progressWidth;
    }
}
