package com.example.aneesh.contactsapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            finish();
            return;
        }
        setContentView(R.layout.activity_contact_profile);

    }

    @Override
    public void onBackPressed() {

        //String data = mEditText.getText();
        //Intent intent = new Intent();
        //intent.putExtra("MyData", data);
        //setResult(resultcode, intent);
    }
}
