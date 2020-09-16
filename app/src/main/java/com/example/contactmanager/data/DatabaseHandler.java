package com.example.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;
import com.example.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    /*
        create our table _name(id, name, phone_number);
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - Structured Query Language
        //creating the table
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE); //Creating our table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //create the table again
        onCreate(db);
    }

    /*
    CRUD = Create, Read, Update, Delete
     */

    //add contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //insert the rows above into your table
        //it's like a hash map structure
        db.insert(Util.TABLE_NAME, null, values);
        Log.d("DBHandler", "addContact: item added");
        db.close(); //closing the db connection
    }

    //get a contact
    //will return a contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        //find the rows in the table using Cursor
        //i want to receive id with =?
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{ Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        //we reset the cursor to read all the rows
        if (cursor != null)
            cursor.moveToFirst();

        //reading through the rows for the contact table
        Contact contact = new Contact();
        //contact.setId(Integer.parseInt(cursor.getString(0)));
        //contact.setName(cursor.getString(1));
        //contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        //select all contacts from the database table
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        //raw Query allows you to send SQL raw code
        Cursor cursor = db.rawQuery(selectAll, null);

        //loop through the data we receive
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //ad contact objects to our list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    //update contact by finding the specific row returning an int
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //update the row
        //update (table name, values, where id = 1)
        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //delete single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    //get contacts count
    public int getCount() {
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

}
