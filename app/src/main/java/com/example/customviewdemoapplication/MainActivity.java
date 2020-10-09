package com.example.customviewdemoapplication;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewdemoapplication.Views.ColorView;
import com.example.customviewdemoapplication.Views.MyView;
import com.example.customviewdemoapplication.Views.password_strength.PasswordStrength;
import com.example.customviewdemoapplication.Views.password_strength.PasswordStrengthBar;
import com.example.customviewdemoapplication.Views.password_strength.PasswordStrengthChangeListener;

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

        final TextView textView = findViewById(R.id.textView);

        final PasswordStrengthBar passwordStrengthBar = findViewById(R.id.passwordStrengthBar);
        passwordStrengthBar.addPasswordStrengthChangeListener(new PasswordStrengthChangeListener() {
            @Override
            public void onPasswordStrengthChanged(PasswordStrength passwordStrength) {
                textView.setText("Password Strength: " + passwordStrength.name());
            }
        });

        EditText editText = findViewById(R.id.edtPassword);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordStrengthBar.setPassword(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }
}
