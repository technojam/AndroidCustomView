package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewdemoapplication.R;

public class ReviewBarView extends View {

    final static int LEFT_GAP = 350;
    static float RIGHT_GAP = 100;
    final static int RECT_HEIGHT = 20;
    final static int RECT_WIDTH = 600;

    float mTextLeftGap;

    String mStringStart, mStringEnd;
    int mBgColor, mFgColor, mStringColor;
    float mPercentage;

    Paint mPaintBar;
    Paint mPaintProgress;
    Paint mPaintText;


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
        mTextLeftGap = typedArray.getDimension(R.styleable.ReviewBarView_margin_left, LEFT_GAP/4);
        RIGHT_GAP = typedArray.getDimension(R.styleable.ReviewBarView_margin_right, 100);
        mStringStart = typedArray.getString(R.styleable.ReviewBarView_string_start);
        mStringEnd = typedArray.getString(R.styleable.ReviewBarView_string_end);
        mStringColor = typedArray.getColor(R.styleable.ReviewBarView_string_color, Color.BLACK);

        typedArray.recycle();

        mRectBar = new Rect();
        mRectProgress = new Rect();

        mPaintBar = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);


        mPaintBar.setColor(mBgColor);
        mPaintProgress.setColor(mFgColor);

        mPaintText.setTextSize(30);
        mPaintText.setColor(mStringColor);





    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectBar.top = getHeight()/2 - RECT_HEIGHT/2;
        mRectBar.left = getWidth() - RECT_WIDTH - (int)RIGHT_GAP;
        mRectBar.bottom = mRectBar.top + RECT_HEIGHT;
        mRectBar.right = mRectBar.left + RECT_WIDTH;


        mRectProgress.top = mRectBar.top;
        mRectProgress.left = mRectBar.left;
        mRectProgress.bottom = mRectBar.bottom;
        mRectProgress.right = mRectProgress.left + getProgressWidth();


        canvas.drawRect(mRectBar, mPaintBar);
        canvas.drawRect(mRectProgress, mPaintProgress);

        canvas.drawText(mStringStart, mTextLeftGap, getHeight()/2 + RECT_HEIGHT/2, mPaintText);
        canvas.drawText(mStringEnd, mRectBar.right + 10, getHeight()/2 + RECT_HEIGHT/2, mPaintText);

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
