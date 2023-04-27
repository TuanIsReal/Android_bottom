package com.example.myapplication.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tuan.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table items(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "cautruc text," +
                "date text," +
                "status text," +
                "countries integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Item> getAll(){
        List<Item> items = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null,
                null,null,
                null,null,null);

        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String ct = rs.getString(2);
            String date = rs.getString(3);
            String status = rs.getString(4);
            int countries = rs.getInt(5);
            items.add(new Item(id, name, ct, date, status, countries));
        }
        return items;
    }

//    public List<Item> getItemsByDate(String date){
//        List<Item> items = new ArrayList<>();
//        String whereClause = "date like ?";
//        String[]  whereArg = {date};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, whereClause, whereArg, null, null, null);
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            items.add(new Item(id, title, category, price, date));
//        }
//        return items;
//    }

//    public List<Item> getItemsByTitle(String key){
//        List<Item> items = new ArrayList<>();
//        String whereClause = "title like ?";
//        String[]  whereArg = {"%" + key +"%"};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, whereClause, whereArg, null, null, null);
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            items.add(new Item(id, title, category, price, date));
//        }
//        return items;
//    }

//    public List<Item> getItemsByCategory(String key){
//        List<Item> items = new ArrayList<>();
//        String whereClause = "category like ?";
//        String[]  whereArg = {"%" + key +"%"};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, whereClause, whereArg, null, null, null);
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            items.add(new Item(id, title, category, price, date));
//        }
//        return items;
//    }

    //    public List<Item> getItemsByDateRange(String from, String to){
//        List<Item> items = new ArrayList<>();
//        String whereClause = "date between ? and ?";
//        String[]  whereArg = {from.trim(), to.trim()};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, whereClause, whereArg, null, null, null);
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            items.add(new Item(id, title, category, price, date));
//        }
//        return items;
//    }
    //insert item
    public long insertItem(Item item){
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("cautruc", item.getCt());
        values.put("date", item.getDate());
        values.put("status", item.getStatus());
        values.put("countries", item.getCountries());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("items", null, values);
    }

    //update
    public int updateItem(Item item ){
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("cautruc", item.getCt());
        values.put("date", item.getDate());
        values.put("status", item.getStatus());
        values.put("countries", item.getCountries());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(item.getId())};
        return st.update("items", values, whereClause, whereArgs);
    }

    //delete
    public int deleteItem(int id){
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("items", whereClause, whereArgs);
    }


}
