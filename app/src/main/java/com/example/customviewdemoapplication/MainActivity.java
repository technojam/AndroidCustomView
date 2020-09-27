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
    //private static final String[] COUNTRIES = new String[]{"asdasdas","aasdasd","weqeqwreqewr","asfasgfqasgwqr"};

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

       /* AutoCompleteTextView textView =  findViewById(R.id.autoCompleteCountry); // get a reference to the autocomplete in the layout

        String[] countries = getResources().getStringArray(R.array.countries_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,COUNTRIES);
        Toast.makeText(MainActivity.this,"sadasd",Toast.LENGTH_LONG).show();
        textView.setAdapter(adapter);*/
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
