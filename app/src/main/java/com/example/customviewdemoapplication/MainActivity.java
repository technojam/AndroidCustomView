package com.example.customviewdemoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.customviewdemoapplication.Views.MyView;

public class MainActivity extends AppCompatActivity {

    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (MyView)findViewById(R.id.customView);

        findViewById(R.id.swapColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.swapColor();
            }
        });


    }
}
