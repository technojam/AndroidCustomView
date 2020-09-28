package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ColorView extends View {

    private static final int SQUARE_SIZE = 200;
    private Rect mRectSquare;
    private Paint mPaintSquare;

    public ColorView(Context context) {
        super(context);
        init(null);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(@Nullable AttributeSet set){
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSquare.setColor(Color.GREEN);
    }

    public void SwapColor() {
        if(mPaintSquare.getColor() == Color.GREEN){
             mPaintSquare.setColor(Color.RED);
        }else if(mPaintSquare.getColor() == Color.RED){
            mPaintSquare.setColor(Color.BLUE);

        }else if(mPaintSquare.getColor() == Color.BLUE){
            mPaintSquare.setColor(Color.YELLOW);
        }else if(mPaintSquare.getColor() == Color.YELLOW) {
            mPaintSquare.setColor(Color.GREEN);
        }
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        mRectSquare.left = 10;
        mRectSquare.top = 10;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;


        canvas.drawRect(mRectSquare,mPaintSquare);
    }
}
