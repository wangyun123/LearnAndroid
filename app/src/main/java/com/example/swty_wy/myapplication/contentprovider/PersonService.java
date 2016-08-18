package com.example.swty_wy.myapplication.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SWTY-WY on 2016/8/18.
 */
public class PersonService {
    private PersonDBOpenHelper dbOpenHelper;

    public PersonService(Context context){
        dbOpenHelper = new PersonDBOpenHelper(context);
    }

    public void save(Person person){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into person(name,age) values(?,?)", new Object[]{person.getName(),person.getAge()});
    }

    public void delete(Integer _id){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from person where _id=?", new Object[]{_id});
    }

    public Person find(Integer _id){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from person where _id=?", new String[]{_id.toString()});
        if (cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            Person person = new Person();
            person.set_id(id);
            person.setName(name);
            person.setAge(age);
            return person;
        }
        return null;
    }

    public List<Person> findAll(){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from person", null);
        List<Person> persons = new ArrayList<Person>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            Person person = new Person();
            person.set_id(id);
            person.setName(name);
            person.setAge(age);
            persons.add(person);
        }
        return persons;
    }
}
