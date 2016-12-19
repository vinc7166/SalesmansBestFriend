package com.example.peter.salesmansbestfriend_finalversion;

/**
 * Created by Peter on 12/11/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TheDataBase extends SQLiteOpenHelper {
    private static final String DB_Name = "EmployeeInfo";
    private static final int DB_Version = 1;
    Context appContext;

    public TheDataBase(Context C) {
        super(C, DB_Name, null, DB_Version);
        appContext = C;
    }

    @Override
    public void onCreate(SQLiteDatabase D) {

        String CreateTable = "Create Table empInfo (" +
                "name varchar(50)," +
                "id varchar(50)," +
                "yearlySalary varchar(50)," +
                "monthlySalary varchar(50)," +
                "quota varchar(50)," +
                "sales varchar(50)" +
                ");";
        D.execSQL(CreateTable);
    }

    public void onUpgrade(SQLiteDatabase D, int oldVer, int newVer) {
        D.execSQL("drop table if exists empInfo");
        onCreate(D);
    }

    public void addRecord(String n, String id, String yS, String mS, String q) {
        SQLiteDatabase D = getWritableDatabase();
        ContentValues V = new ContentValues();
        V.put("name", n);
        V.put("id", id);
        V.put("yearlySalary", yS);
        V.put("monthlySalary", mS);
        V.put("quota", q);
        V.put("sales", "0");
        D.insert("empInfo", null, V);
        D.close();
    }

    public void updateSales(String n, String s) {
        SQLiteDatabase D = getWritableDatabase();
        ContentValues V = new ContentValues();
        V.put("sales", s);
        D.update("empInfo", V, "name='" + n + "'", null);
        D.close();
    }

    public void clearSales(String nameS) {
        SQLiteDatabase D = getWritableDatabase();
        ContentValues V = new ContentValues();
        V.put("sales", "0");
        D.update("empInfo", V, "name='" + nameS + "'", null);
        D.close();
    }

    public void deleteEmp(String name){
        SQLiteDatabase D = getWritableDatabase();
        try {
            D.delete("empInfo", "name = ?", new String[]{name});
        }catch (Exception E){

        }finally {
            D.close();
        }
    }

    public TheDataBaseEmpRecord showRecord(String nameS) {
        SQLiteDatabase D = getReadableDatabase();
        String fieldNames[] = new String[]{"name", "id", "yearlySalary", "monthlySalary", "quota", "sales"};

        Cursor C = D.query(true, "empInfo", fieldNames, null, null, null, null, null, null);
        C.moveToFirst();

        while (!nameS.equals(C.getString(C.getColumnIndex("name")))) {
            C.moveToNext();
        }
        String name = C.getString(C.getColumnIndex("name"));
        String id = C.getString(C.getColumnIndex("id"));
        String yS = C.getString(C.getColumnIndex("yearlySalary"));
        String mS = C.getString(C.getColumnIndex("monthlySalary"));
        String q = C.getString(C.getColumnIndex("quota"));
        String s = C.getString(C.getColumnIndex("sales"));

        TheDataBaseEmpRecord rec = new TheDataBaseEmpRecord(name, id, yS, mS, q, s);
        return rec;
    }

    public String grabNames() {
        String nameString = "Add Employee:";
        SQLiteDatabase D = getReadableDatabase();
        String fieldNames[] = new String[]{"name", "id", "yearlySalary", "monthlySalary", "quota", "sales"};
        Cursor C = D.query(true, "empInfo", fieldNames, null, null, null, null, null, null);
        C.moveToFirst();
        do {
            nameString += C.getString(C.getColumnIndex("name")) + ":";
        }while(C.moveToNext());
        return nameString;
    }
}
