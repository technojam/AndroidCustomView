package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import com.example.customviewdemoapplication.R;

public class ReviewBarView extends View {

    final static int GAP = 50;

    int mBgColor, mFgColor;
    float mPercentage;

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

        canvas.drawRect(mRectBar, mPaintBar);
    }
}
