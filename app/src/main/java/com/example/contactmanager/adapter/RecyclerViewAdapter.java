package com.example.contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanager.DetailsActivity;
import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    /*
        Where we place our views so they will be recycled from the XML files using layout inflater
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row, parent, false);
        //once we inflate the view as an object view,
        //we want to pass it as a ViewHolder constructor inside the view
        return new ViewHolder(view);
    }

    /*
        We're binding the view with the data using recyclerview
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //we use the index to get each contact object inside of our list
        Contact contact = contactList.get(position);
        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());

    }

    /*
        The data coming in the recycler view must have a count returning the array size.
     */
    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            //instantiate the XML contact_row class
            itemView.setOnClickListener(this);
            contactName = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            iconButton = itemView.findViewById(R.id.icon_button);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Contact contact = contactList.get(position);

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", contact.getName());
            intent.putExtra("phone", contact.getPhoneNumber());

            context.startActivity(intent);

            /*
            switch (view.getId()) {
                case R.id.icon_button:
                    Log.d("IconClicked", "onClick: " + contact.getName());
                    break;
            }

            Log.d("Clicked", "onClick: " + contact.getName());
             */


        }
    }
}
