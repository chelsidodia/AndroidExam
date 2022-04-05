package com.example.testcrud1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtEname,txtSalary,txtDesignation,txtDepartment;
    Button btnInsert,btnShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEname = (EditText) findViewById(R.id.txtEname);
        txtSalary = (EditText) findViewById(R.id.txtSalary);
        txtDesignation = (EditText) findViewById(R.id.txtDesignation);
        txtDepartment = (EditText) findViewById(R.id.txtDepartment);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnShow = (Button) findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                String ename = txtEname.getText().toString();
                String sal = txtSalary.getText().toString();
                String des = txtDesignation.getText().toString();
                String dep = txtDepartment.getText().toString();
                Employee emp = new Employee(ename,sal,des,dep);
                String msg = dbHelper.insert(emp);
                Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(),ShowActivity.class);
                startActivity(it);
            }
        });
    }
}