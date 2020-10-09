package com.example.customviewdemoapplication.Views.password_strength;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customviewdemoapplication.R;

public class PasswordStrengthBar extends View {
    public PasswordStrengthBar(Context context) {
        super(context);
        init(null, 0);
    }

    public PasswordStrengthBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PasswordStrengthBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private int CORNER_RADIUS = 0;
    private int MIN_LENGTH = 6;
    private int MAX_LENGTH = 15;

    private PasswordStrengthChangeListener passwordStrengthChangeListener;

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.PasswordStrengthBar, defStyleAttr, 0);
        if (typedArray != null) {
            this.CORNER_RADIUS = typedArray.getDimensionPixelSize(R.styleable.PasswordStrengthBar_cornerRadius, CORNER_RADIUS);
            this.MIN_LENGTH = typedArray.getInt(R.styleable.PasswordStrengthBar_minLength, MIN_LENGTH);
            this.MAX_LENGTH = typedArray.getInt(R.styleable.PasswordStrengthBar_minLength, MAX_LENGTH);

            String password = typedArray.getString(R.styleable.PasswordStrengthBar_password);
            if (password != null) {
                setPassword(password);
            }

            typedArray.recycle();
        }

    }

    public void addPasswordStrengthChangeListener(PasswordStrengthChangeListener passwordStrengthChangeListener) {
        this.passwordStrengthChangeListener = passwordStrengthChangeListener;
    }

    public void setPassword(String password) {
        PasswordStrength passwordStrength = PasswordStrength.calculate(password, MIN_LENGTH, MAX_LENGTH);

        if (passwordStrengthChangeListener != null) {
            passwordStrengthChangeListener.onPasswordStrengthChanged(passwordStrength);
        }

        setBackground(getBackgroundDrawable(passwordStrength));
    }

    public void setPassword(CharSequence password) {
        setPassword(password.toString());
    }


    private GradientDrawable getBackgroundDrawable(PasswordStrength passwordStrength) {

        int SIZE = 100;
        int LIMIT = getColorLimit(passwordStrength);
        int[] colorList = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            if (i <= LIMIT) {
                colorList[i] = getColor(i);
            } else {
                colorList[i] = 0xFFFFFFFF;
            }
        }


        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, colorList);
        drawable.setCornerRadius(CORNER_RADIUS);
        return drawable;
    }

    private int getColorLimit(PasswordStrength passwordStrength) {
        switch (passwordStrength) {
            case WEAK:
                return 25;
            case MEDIUM:
                return 50;
            case STRONG:
                return 75;
            case VERY_STRONG:
                return 100;
            default:
                throw new IllegalStateException("Unexpected value: " + passwordStrength);
        }
    }

    public int getColor(int n) {
        int R, G, B, A;
        n = 100 - n;

        R = (int) (255 * Math.sqrt(Math.sin(n * Math.PI / 200)));
        G = (int) (255 * Math.sqrt(Math.cos(n * Math.PI / 200)));
        B = 0;
        A = 0xFF;

        return (A & 0xFF) << 24 | (R & 0xFF) << 16 | (G & 0xFF) << 8 | (B & 0xFF);
    }
}
