package com.example.customviewdemoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.customviewdemoapplication.Views.MyView;
import com.example.customviewdemoapplication.Views.RoomTypeView;

public class MainActivity extends AppCompatActivity {

    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final RoomTypeView roomTypeView = (RoomTypeView)findViewById(R.id.roomTypeView);
        Button get = (Button)findViewById(R.id.getButton);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, ""+roomTypeView.getmCounter(), Toast.LENGTH_SHORT).show();
            }
        });*/





    }
}
