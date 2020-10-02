package com.example.contactmanager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanager.adapter.RecyclerViewAdapter;
import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycer_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.d("MainActivity", "onCreate: " + contact.getName());
            contactArrayList.add(contact);
        }

        //setup adapter
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        /* data manually typed in
        db.addContact(new Contact("James", "07398783278"));
        db.addContact(new Contact("Debra", "07328748932"));
        db.addContact(new Contact("Helena", "08328932902"));
        db.addContact(new Contact("Carimo", "02903284871"));
        db.addContact(new Contact("John", "02908387871"));
        db.addContact(new Contact("Guerro", "08307946934"));
        db.addContact(new Contact("Victor", "09823728743"));
        db.addContact(new Contact("Gemma", "92738978974"));
        */

        /* Practicing Database CRUD functions
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
         */

    }


}