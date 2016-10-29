package com.example.aneesh.contactsapp;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortraitContactProfile extends Fragment {


    public ArrayList<Contact> relation;
    public TextView contactName;
    public TextView contactNumber;
    public ListView relationship;
    public Contact contact;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portrait_contact_profile, container, false);

        contactName = (TextView) view.findViewById(R.id.contactName);
        contactNumber = (TextView)view.findViewById(R.id.contactNumber);
        relationship = (ListView)view.findViewById(R.id.relationList);


        contact = (Contact) getActivity().getIntent().getSerializableExtra("contacts");
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && contact != null){

            contactName.setText(contact.getName());
            contactNumber.setText(contact.getContactNumber());

            relation = contact.getRelationship();
            ArrayList<String>relationArray = new ArrayList<>();
            for(int i = 0; i < relation.size(); i++){
                relationArray.add(relation.get(i).getName());
            }
            populateSimpleList(relationArray);
        }

        return view;
    }

    public void landscapeModifications(Contact contact){

        contactName.setText(contact.getName());
        System.out.println(contactName.getText().toString());

        contactNumber.setText(contact.getContactNumber());

        relation = contact.getRelationship();
        ArrayList<String>relationArray = new ArrayList<>();
        for(int i = 0; i < relation.size(); i++){
            relationArray.add(relation.get(i).getName());
        }
        populateSimpleList(relationArray);
    }

    public void populateSimpleList(ArrayList<String> rel){
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_text_view,
                R.id.contactName,
                rel
        );

        relationship.setAdapter(simpleAdapter);
    }


}

