package com.example.aneesh.contactsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PortraitContactDetails extends Fragment {

    private EditText name;
    private EditText phoneNumber;
    private ListView listView;
    public ArrayList<Contact> contacts = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portrait_contact_details, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        name = (EditText)view.findViewById(R.id.contactName);
        phoneNumber = (EditText)view.findViewById(R.id.contactNumber);
        listView = (ListView)view.findViewById(R.id.contactListView);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if(savedInstanceState != null)
                contacts = (ArrayList<Contact>) savedInstanceState.getSerializable("contacts");
            else
                contacts = (ArrayList<Contact>) getActivity().getIntent().getSerializableExtra("contacts");
            populateList(contacts);
        }else{
            Bundle bundle = this.getArguments();
            if(bundle != null){     //added while in class//////////////////
                contacts = (ArrayList<Contact>) bundle.getSerializable("contacts");
                populateList(contacts);
            }
        }


        final Button saveButton = (Button)view.findViewById(R.id.addPerson);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonClick(v);
            }
        });

        return view;
    }

    /*public void setArrayList(ArrayList<Contact> c){
        contacts = c;
    }*/

    public void populateList(ArrayList<Contact> contacts){
        ListAdapter customAdapter = new CustomAdapter(getActivity(), false, contacts);
        listView.setAdapter(customAdapter);
    }

    public void saveButtonClick(View view) {
        Contact contact = new Contact(name.getText().toString(), phoneNumber.getText().toString());

        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).isRelationBool()){
                System.out.println(i);
                contact.addRelationship(contacts.get(i));
                contacts.get(i).addRelationship(contact);
            }
        }
        contacts.add(contact);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Intent intent = new Intent();
            intent.putExtra("contacts", contacts);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
        else{
            MainActivity m = (MainActivity)getActivity();
            m.detailsFragDataPass(contacts);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //ContactDetails p = (ContactDetails)getActivity();
        //p.viewChange(contacts);
        outState.putSerializable("contacts", contacts);
    }
}
