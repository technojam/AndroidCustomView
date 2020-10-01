package com.example.customviewdemoapplication.Views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customviewdemoapplication.MainActivity;
import com.example.customviewdemoapplication.R;

public class batteryreviwer extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        TextView statuslabel = ((MainActivity)context).findViewById(R.id.statusLabel);
        TextView batterylevel =((MainActivity)context).findViewById(R.id.percentageLable);
        ImageView batteryImage = ((MainActivity)context).findViewById(R.id.batteryimage);


        String action = intent.getAction();
        if(action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)){

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            String message = "";

            switch (status){
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "full";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    message = "Charging";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "Discharge";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "Not charging";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    message = "Unknown";
                    break;
            }

            statuslabel.setText(message);

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            int percentage = level * 100 /scale;
            batterylevel.setText(percentage + "%");


            Resources res = context.getResources();

            if (percentage >=  90 ){
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b100));

            }
            else if (90 > percentage && percentage >=65 ){
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b75));

            }
            else if (65 > percentage && percentage >=40){
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b50));
            }
            else  if ( 40 > percentage && percentage >= 15){
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b25));
            }
            else {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b0));
            }





        }

    }
}
