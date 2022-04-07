package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnsave;
    EditText txtfname,txtlname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtfname = findViewById(R.id.txtfname);
        txtlname = findViewById(R.id.txtlname);
        btnsave = findViewById(R.id.btnsave);
        SharedPreferences spf2 = getSharedPreferences("mypref",MODE_PRIVATE);
        if(spf2.contains("fname"))
        {
            txtfname.setText(spf2.getString("fname",""));
        }
        if(spf2.contains("lname"))
        {
            txtlname.setText(spf2.getString("lname",""));
        }
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences spf = getSharedPreferences("mypref",MODE_PRIVATE);
                SharedPreferences.Editor edit = spf.edit();
                edit.putString("fname",txtfname.getText().toString());
                edit.putString("lname",txtlname.getText().toString());
                edit.apply();
            }
        });
    }
}