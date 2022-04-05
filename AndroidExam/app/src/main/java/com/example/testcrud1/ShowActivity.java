package com.example.testcrud1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ShowActivity extends AppCompatActivity {
    ListView listView;
    private SimpleCursorAdapter adapter;
    DBHelper dbHelper;
    final String[] from = new String[] { dbHelper._id,
            dbHelper.empname, dbHelper.salary,dbHelper.designation,dbHelper.department };
    Cursor cursor;
    final int[] to = new int[] { R.id.tv1, R.id.tv2, R.id.tv3 ,R.id.tv4,R.id.tv5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_show);
            listView = (ListView) findViewById(R.id.lv);
            dbHelper = new DBHelper(getApplicationContext());
            cursor = dbHelper.fetch();
            Toast.makeText(getApplicationContext(), "Cursor"+cursor.getCount(), Toast.LENGTH_LONG).show();
            listView = (ListView) findViewById(R.id.lv);


            adapter = new SimpleCursorAdapter(this, R.layout.rowlayout, cursor, from, to, 0);
            adapter.notifyDataSetChanged();

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                    cursor.moveToPosition(position);
                    String id=cursor.getString(0);
                    Toast.makeText(getApplicationContext()," ID to "+id,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ModifyActivity.class);
                    intent.putExtra("id", id);

                    startActivity(intent);
                }
            });
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"err"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
