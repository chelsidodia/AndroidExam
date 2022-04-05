package com.example.testcrud1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {
    Button bedit, bdelete;
    EditText edid,edname,edlname,eddes,eddep;
    DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        edid=(EditText)findViewById(R.id.ed);
        edname=(EditText)findViewById(R.id.ed1);
        edlname=(EditText)findViewById(R.id.ed2);
        eddes = (EditText) findViewById(R.id.ed3);
        eddep = (EditText) findViewById(R.id.ed4);

        bedit=(Button)findViewById(R.id.btnEdit);
        bdelete=(Button)findViewById(R.id.btnDelete);

        databaseHelper = new DBHelper(getApplicationContext());
        Intent it=getIntent();
        int id=Integer.parseInt( it.getStringExtra("id"));
        Toast.makeText(getApplicationContext(),"ID "+id, Toast.LENGTH_LONG).show();
        Employee ss=databaseHelper.SearchStudent(id);

        if(ss!=null) {
            edid.setText(ss.getEmpid()+"");
            edname.setText(ss.getEmpname());
            edlname.setText(ss.getSalary());
            eddes.setText(ss.getDesignation());
            eddep.setText(ss.getDepartment());
            Toast.makeText(getApplicationContext()," Student found ", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "No Student found ", Toast.LENGTH_LONG).show();

        }

        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper = new DBHelper(getApplicationContext());
                int id = Integer.parseInt(edid.getText().toString());
                String msg= databaseHelper.delete(id);
                Toast.makeText(getApplicationContext(),"Deleted "+msg, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(ModifyActivity.this,ShowActivity.class);
                startActivity(intent);

            }
        });

        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MyDBHelper dbHandler = new MyDBHelper(getApplicationContext(), null, null, 1);
                databaseHelper = new DBHelper(getApplicationContext());
                int id = Integer.parseInt(edid.getText().toString());
                String name = edname.getText().toString();
                String lname = edlname.getText().toString();
                String desig = eddes.getText().toString();
                String dept = eddep.getText().toString();
                Employee emp = new Employee(id, name,lname,desig,dept);
                String msg= databaseHelper.update(emp);
                Toast.makeText(getApplicationContext(),"Updated "+msg, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(ModifyActivity.this,ShowActivity.class);
                startActivity(intent);

            }
        });
    }

}