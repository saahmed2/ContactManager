package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        Log.d("Count", "onCreate: " + db.getCount());

        //creating a contact object
        Contact jeremy = new Contact();
        jeremy.setName("Jeremiah");
        jeremy.setPhoneNumber("0782648392");

        Contact jason = new Contact();
        jason.setName("Jason");
        jason.setPhoneNumber("07826483874");

        db.addContact(jeremy);
        //db.addContact(jason);

        //get one contact to update or delete
        Contact c = db.getContact(2);
        //update one contact (the second contact)
        c.setName("NewJeremy");
        c.setPhoneNumber("273987497");

        //int updateRow = db.updateContact(c);
        //Log.d("Updated Row", "onCreate: " + updateRow);

        //delete a contact
        db.deleteContact(c);


        Log.d("Main", "onCreate: " + c.getName() + ", " +  c.getPhoneNumber());

        List<Contact> contactList = db.getAllContacts();

        //the contact list will pass the contact
        for (Contact contact : contactList) {
            Log.d("hello", "onCreate: " + contact.getId());
            Log.d("hello", "onCreate: " + contact.getName());
            Log.d("hello", "onCreate: " + contact.getPhoneNumber());

        }
    }


}