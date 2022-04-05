package com.example.testcrud1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String tablename = "employeetb";
    public static final String _id = "_id";
    public static final String empname = "employeename";
    public static final String salary = "salary";
    public static final String designation = "designation";
    public static final String department = "department";

    SQLiteDatabase database;
    static final String dbname = "employee1.db";

    Context context;

    static final int dbversion = 1;

    private static final String createtable = "create table " + tablename + "(" + _id + " integer primary key autoincrement," + empname + " text not null," + salary + " text not null," + designation + " text not null," + department + " text not null);";



    public DBHelper(@Nullable Context context) {
        super(context, dbname, null, dbversion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public String insert(Employee e)
    {
        try{
            database =this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(empname,e.getEmpname());
            contentValues.put(salary,e.getSalary());
            contentValues.put(designation,e.getDesignation());
            contentValues.put(department,e.getDepartment());
            long id =database.insert(tablename,null,contentValues);
            Toast.makeText(context, "Inserted " + id, Toast.LENGTH_LONG).show();
            return "Inserted " + id;
        }
        catch (Exception ex)
        {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return ex.getMessage();
        }
    }

    public Cursor fetch()
    {
        database =this.getWritableDatabase();
        String[] columns = new String[] { _id,empname,salary,designation,department };
        Cursor cursor = database.query(tablename,columns,null,null,null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Employee SearchStudent(int _id) {
        database = this.getReadableDatabase();

        Employee s=new Employee();
        Cursor cursor = database.query(tablename, null, "_id=?", new String[]{_id+""}, null, null, null);
        Toast.makeText(context, "Found "+cursor.getCount() , Toast.LENGTH_LONG).show();
        if (cursor.getCount()>0) {

            cursor.moveToFirst();
//            Toast.makeText(context, "Student"+cursor.getString(1) , Toast.LENGTH_LONG).show();
            s.setEmpid(cursor.getInt(0));
            s.setEmpname(cursor.getString(1));
            s.setSalary(cursor.getString(2));
            s.setDesignation(cursor.getString(3));
            s.setDepartment(cursor.getString(4));
            //Toast.makeText(context, "Student Found "+s.getStudentName() + s.getID() +s.getStudentLName(), Toast.LENGTH_LONG).show();

        }
        return s;
    }

    public String update(Employee e) {
        database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(empname, e.getEmpname());
        contentValues.put(salary, e.getSalary());
        contentValues.put(designation, e.getDesignation());
        contentValues.put(department,e.getDepartment());
        int i = database.update( tablename, contentValues,  _id + " = " + e.getEmpid(), null);
        return "Updated"+i;
    }

    public String delete(int _id2) {

        database = this.getWritableDatabase();

        long id=database.delete(tablename,  _id + "=" + _id2, null);
        return  "Deleted "+id;
    }

}
