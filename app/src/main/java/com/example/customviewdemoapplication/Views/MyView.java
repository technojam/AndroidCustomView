package com.example.customviewdemoapplication.Views;

import android.content.pm.LauncherApps;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import com.example.customviewdemoapplication.R;


public class MyView extends View {

    public static final int SQUARE_SIZE = 350;
    private Rect mRectSquare;
    private Paint mPaintSquare;

    private int mSquareColor;
    private int mSquareSize;

    private Paint mPaintCircle;

    private float mCircleX, mCircleY;
    private float mCircleRadius = 100f;

    private Bitmap mImage;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    private void init(@Nullable AttributeSet set){
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.parseColor("#00ccff"));

        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.demo);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mImage = getResizedBitmap(mImage, getWidth(), getHeight());
            }
        });

        if(set == null){
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.MyView);

        mSquareColor = typedArray.getColor(R.styleable.MyView_square_color, Color.GREEN);
        mSquareSize = typedArray.getDimensionPixelSize(R.styleable.MyView_square_size, SQUARE_SIZE);

        mPaintSquare.setColor(mSquareColor);
        typedArray.recycle();

    }



    public void swapColor(){
        mPaintSquare.setColor(mPaintSquare.getColor() == mSquareColor ? Color.RED : mSquareColor);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mRectSquare.left = 50;
        mRectSquare.top = 50;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;
        mRectSquare.right = mRectSquare.left + mSquareSize;

        canvas.drawRect(mRectSquare, mPaintSquare);

        if(mCircleX == 0f || mCircleY == 0f){
            mCircleX = getWidth() / 2;
            mCircleY = getHeight() / 2;
        }

        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);

        //canvas.drawBitmap(mImage, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(100f);

        canvas.drawText("Hello World", getWidth()/2, getHeight()/2, paint);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if(mRectSquare.left < x && mRectSquare.right > x){
                    if(mRectSquare.top < y && mRectSquare.bottom > y){
                        mCircleRadius += 10f;
                        postInvalidate();
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:

                double dx = Math.pow(x - mCircleX, 2);
                double dy = Math.pow(y - mCircleY, 2);

                if(dy + dx < Math.pow(mCircleRadius, 2)){
                    mCircleX = x;
                    mCircleY = y;

                    postInvalidate();
                    return true;

                }
                return value;

        }

        return value;
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int reqWidth, int reqHeight){
        Matrix matrix = new Matrix();

        RectF src = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0, 0, reqWidth, reqHeight);

        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
