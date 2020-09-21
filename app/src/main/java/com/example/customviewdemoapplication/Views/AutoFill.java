package com.example.customviewdemoapplication.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;

import com.example.customviewdemoapplication.R;

public class AutoFill extends View {

    public AutoFill(Context context,@Nullable AttributeSet attrs){
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteCountry); // get a reference to the autocomplete in the layout

        String[] countries = getResources().getStringArray(R.array.countries_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,countries);
        textView.setAdapter(adapter);
    }


}
