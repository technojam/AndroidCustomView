package com.example.customviewdemoapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewdemoapplication.Views.ColorView;

public class MainActivity extends AppCompatActivity {

    private ColorView mColorView;
    private TextView battery;
    public int deviceStatus;
    private TextView batteryStatus;
    private BroadcastReceiver batInfo = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            battery.setText(String.valueOf(level)+"%");
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);

            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){

                batteryStatus.setText("Charging");

            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){

                batteryStatus.setText("Discharging");

            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorView = (ColorView) findViewById(R.id.colorView);

        Button btn =(Button) findViewById(R.id.btn_swap_color);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mColorView.SwapColor();
            }
        });


        battery = (TextView) findViewById(R.id.batteryView);
        this.registerReceiver(this.batInfo,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        batteryStatus = (TextView)findViewById(R.id.batteryStatus);
        this.registerReceiver(this.batInfo,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }
}
