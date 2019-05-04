package com.example.basefeature;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Retrofit;

public class RegisterActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        Button button = findViewById(R.id.register);

        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                String username, password, name, surname, phone;
                EditText editText = findViewById(R.id.username);
                username = editText.getText().toString();
                editText = findViewById(R.id.password);
                password = editText.getText().toString();
                if(!password.contains("[a-z]+[0-9]")){
                    //bad
                }
                editText = findViewById(R.id.name);
                name = editText.getText().toString();
                editText = findViewById(R.id.second_name);
                surname  = editText.getText().toString();
                editText = findViewById(R.id.phone_number);
                phone = editText.getText().toString();




                break;


        }
    }

}
