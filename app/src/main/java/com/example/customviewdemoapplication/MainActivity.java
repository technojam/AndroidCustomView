package com.example.customviewdemoapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.customviewdemoapplication.Views.ColorView;
import com.example.customviewdemoapplication.Views.MyView;

public class MainActivity extends AppCompatActivity {

    private MyView myView;
    private ColorView mColorView;
    

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




    }
}
