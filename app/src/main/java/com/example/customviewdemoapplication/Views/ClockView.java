package com.example.customviewdemoapplication.Views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;

public class ClockView extends View {

    private Paint paint;
    int hours;
    int mins;
    int secs;
    int clockStroke;
    Color clockColor;
    int clockBackColor;
    int clockStrokeColor;
    private boolean stopClock;
    private Paint textPaint;
    private int clockTextColor;
    private int textSize;
    private Paint internPaint;


    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void setSystemTime(int hour, int minutes, int seconds){
        this.hours=hour;
        this.mins=minutes;
        this.secs=seconds;
        this.invalidate();
    }

    public void refreshTime(){
        setSystemTime();
        this.invalidate();
    }

    private void init(Context context,@Nullable AttributeSet attrs){
        try {

            this.setId(getId());
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.clock_view, 0, 0);
            try {
                hours=a.getInteger(R.styleable.clock_view_hours,0);
                mins=a.getInteger(R.styleable.clock_view_minutes,0);
                secs=a.getInteger(R.styleable.clock_view_secs,0);
                clockStroke=a.getDimensionPixelSize(R.styleable.clock_view_clockStroke,3);
                textSize=a.getDimensionPixelSize(R.styleable.clock_view_textSize,10);
                clockStrokeColor=a.getColor(R.styleable.clock_view_clockColor,Color.BLACK);
                clockBackColor=a.getColor(R.styleable.clock_view_clockBackColor,Color.WHITE);
                clockTextColor=a.getColor(R.styleable.clock_view_textColor,Color.GRAY);
            } finally {
                a.recycle();
            }
        }catch (Exception e){
            Log.i("liunf",e.toString());
        }

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(clockStroke);
        paint.setColor(clockStrokeColor);

        internPaint = new Paint();
        internPaint.setStyle(Paint.Style.FILL);
        internPaint.setStrokeWidth(clockStroke);
        internPaint.setColor(clockBackColor);

        textPaint=new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(clockTextColor);
        textPaint.setTextSize(textSize);

        if( hours==0 ||mins==0||secs==0){
            setSystemTime();
        }
    }


    private void setSystemTime(){
        Date d=Calendar.getInstance().getTime();
        DateFormat df=new SimpleDateFormat("HH:mm:ss");
        String[] timeArray=df.format(d).toString().split(":");

        hours= Integer.parseInt(timeArray[0]);
        mins= Integer.parseInt(timeArray[1]);
        secs= Integer.parseInt(timeArray[2]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float radius=0;
        float viewWidth=getWidth();
        float viewHeight=getHeight();
        float cx=viewWidth/2;
        float cy=viewHeight/2;
        float minuteLen;
        float hourLen;

        if(viewWidth<viewHeight){
            radius= (float) (0.9*viewWidth/2);
        }else{
            radius= (float) (0.9*viewHeight/2);
        }
        minuteLen= (float) (0.85*radius);
        hourLen= (float) (0.5*radius);

        canvas.drawCircle(cx,cy, (float) (radius),internPaint);
        canvas.drawCircle(cx,cy,radius,paint);

        float[] minutePoints=getMinutePoints(mins, (float) (minuteLen),cx,cy);
        canvas.drawLine(cx,cy,minutePoints[0],minutePoints[1],paint);

         float[] hourPoints=getHourPoints(hours%12,mins, (float) (hourLen),cx,cy);
        canvas.drawLine(cx,cy,hourPoints[0],hourPoints[1],paint);

        for(int hourNum=1, i=0;i<360;i+=30,hourNum++){
            float[] numberPoints=getHourPoint(cx,cy, (float) (0.6*radius),i+30);
            canvas.drawText(String.valueOf(hourNum),numberPoints[0],numberPoints[1],textPaint);
        }
    }


    private float[] getHourPoint(float midX,float midY,float radius,float theta){

        int quadrant=0;
        if(theta<=90){
            theta=90-theta;
            quadrant=1;
        }else if(theta>90 && theta<=180){
            theta=theta-90;
            quadrant=2;
        }else if(theta>180 && theta<=270){
            theta=270-theta;
            quadrant=3;
        }else if(theta>270 && theta<=360){
            theta=theta-270;
            quadrant=4;
        }

        float xValue= (float) (radius*Math.cos(Math.toRadians(theta)));
        float yValue = (float) (radius  * Math.sin(Math.toRadians(theta)));
        if(quadrant==1){
            xValue+=midX;
            yValue= (float) (midY-yValue);
        }else if(quadrant==2){
            xValue+=midX;
            yValue= (float) (midY+yValue);
        }else if(quadrant==3){
            xValue=midX-xValue;
            yValue= (float) (midY+yValue);
        }else if(quadrant==4){
            xValue=midX-xValue;
            yValue= (float) (midY-yValue);
        }
        return new float[]{xValue,yValue};
    }


    private float[] getMinutePoints(int mins, float radius, float midX, double midY) {

        float theta=mins*6;
        int quadrant=1;
        if(theta<=90){
            theta=90-theta;
            quadrant=1;
        }else if(theta>90 && theta<=180){
            theta=theta-90;
            quadrant=2;
        }else if(theta>180 && theta<=270){
            theta=270-theta;
            quadrant=3;
        }else if(theta>270 && theta<=360){
            theta=theta-270;
            quadrant=4;
        }

        float xValue= (float) (radius*Math.cos(Math.toRadians(theta)));
        float yValue = (float) (radius  * Math.sin(Math.toRadians(theta)));
        if(quadrant==1){
            xValue+=midX;
            yValue= (float) (midY-yValue);
        }else if(quadrant==2){
            xValue+=midX;
            yValue= (float) (midY+yValue);
        }else if(quadrant==3){
            xValue=midX-xValue;
            yValue= (float) (midY+yValue);
        }else if(quadrant==4){
            xValue=midX-xValue;
            yValue= (float) (midY-yValue);
        }

        return new float[]{xValue,yValue};
    }

    private double getLength(float x1, float y1, float x2, float y2){
        return Math.sqrt(   (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
    private float[] getHourPoints(int hours,int mins, float radius, float midX, float midY) {

        float theta=(hours*5+mins/12)*6;
        int quadrant=1;
        if(theta<=90){
            theta=90-theta;
            quadrant=1;
        }else if(theta>90 && theta<=180){
            theta=theta-90;
            quadrant=2;
        }else if(theta>180 && theta<=270){
            theta=270-theta;
            quadrant=3;
        }else if(theta>270 && theta<=360){
            theta=theta-270;
            quadrant=4;
        }

        float xValue= (float) (radius*Math.cos(Math.toRadians(theta)));
        float yValue = (float) (radius *  Math.sin(Math.toRadians(theta)));


        if(quadrant==1){
            xValue+=midX;
            yValue= (float) (midY-yValue);
        }else if(quadrant==2){
            xValue+=midX;
            yValue= (float) (midY+yValue);
        }else if(quadrant==3){
            xValue=midX-xValue;
            yValue= (float) (midY+yValue);
        }else if(quadrant==4){
            xValue=midX-xValue;
            yValue= (float) (midY-yValue);
        }


        return new float[]{xValue,yValue};
    }

}
