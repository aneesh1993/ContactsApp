package com.example.aneesh.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class ContactDetails extends AppCompatActivity {

    ArrayList<Contact> contacts;
    EditText name, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //ArrayList<Contact> contacts = (ArrayList<Contact>) savedInstanceState.getSerializable("contacts");
            name = (EditText)findViewById(R.id.contactName);
            phone = (EditText)findViewById(R.id.contactNumber);

            Intent intent = new Intent();

            //intent.putExtra("name", name.getText().toString());
            //intent.putExtra("number", phone.getText().toString());
            //intent.putExtra("contacts", contacts);
            setResult(Activity.RESULT_OK, intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_contact_details);
    }

    public void viewChange(ArrayList<Contact> c){
        System.out.println("onViewChange");
        contacts = c;

    }
}
