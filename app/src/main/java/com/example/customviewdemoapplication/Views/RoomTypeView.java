package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewdemoapplication.R;

public class RoomTypeView extends View {

    String mText;
    int mCounter;
    int mIcIncrement;
    int mIcDecrement;
    


    public RoomTypeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.RoomTypeView);

    }



}
