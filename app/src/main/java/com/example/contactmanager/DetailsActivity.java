package com.example.contactmanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private TextView detailsName;
    private TextView detailsPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsName = findViewById(R.id.details_name);
        detailsPhone = findViewById(R.id.details_phone);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            String phone = bundle.getString("phone");

            detailsName.setText(name);
            detailsPhone.setText(phone);
        }
    }
}