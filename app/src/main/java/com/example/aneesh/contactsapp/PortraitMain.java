package com.example.aneesh.contactsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class PortraitMain extends Fragment {

    private ListView listView;

    private static final int REQ_CODE_CONTACT_DETAILS = 1234;
    private static final int REQ_CODE_CONTACT_PROFILE = 4321;


    public ArrayList<Contact> contacts = new ArrayList<>();

    LeftSectionListener activityCommander;
    public interface LeftSectionListener{
        public void changeRightSection(Contact contact);
    }
    AddButtonLandListener activityCommander1;
    public interface AddButtonLandListener{
        public void addInRightSection();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (LeftSectionListener)context;
            activityCommander1 = (AddButtonLandListener) context;
        }catch (Exception e){
            Log.wtf("interface", e);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_portrait_main, container, false);
        listView = (ListView)view.findViewById(R.id.contactListView);
        ////////////////////////////////////////// Testing Purpose ///////////////////////////////

        Contact contact1 = new Contact("Aneesh1", "(848) 237 9926");
        Contact contact2 = new Contact("Aneesh2", "(848) 237 9927");
        Contact contact3 = new Contact("Aneesh3", "(848) 237 9928");


        if (savedInstanceState == null){
            contacts.add(contact1);
            contacts.add(contact2);
            contacts.add(contact3);
            populateList(contacts);
        }
        else {
            contacts = (ArrayList<Contact>)savedInstanceState.getSerializable("contactArrayList");
            System.out.println(contacts.get(0).getName());
            if(contacts != null)
                populateList(contacts);
        }



        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            MainActivity m = (MainActivity) getActivity();
            m.arrayListInit(contacts);
        }


        //////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////// addButton Click Listener ////////////////////////////////
        final Button addButton = (Button)view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClick(v);
            }
        });

        ///////////////////////////// deleteButton Click Listener ////////////////////////////////
        final Button deleteButton = (Button)view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonClick(v);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    Intent intent = new Intent(getActivity(), ContactProfile.class);
                    intent.putExtra("contacts", contacts.get(position));
                    startActivity(intent);
                }
                else{
                    //////////////////////////////////////////
                    activityCommander.changeRightSection(contacts.get(position));
                }
            }
        });
        return view;
    }

    public void addButtonClick(View view){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Intent intent = new Intent(getActivity(), ContactDetails.class);
            intent.putExtra("contacts", contacts);
            startActivityForResult(intent, REQ_CODE_CONTACT_DETAILS);
        }
        else{
            activityCommander1.addInRightSection();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_CONTACT_DETAILS && resultCode == Activity.RESULT_OK){
            System.out.println("onactresult");
            contacts = (ArrayList<Contact>) data.getSerializableExtra("contacts");
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("number");
            if(contacts != null){
                ListAdapter customAdapter = new CustomAdapter(getActivity(), true, contacts);
                listView.setAdapter(customAdapter);

                MainActivity m = (MainActivity)getActivity();

                m.arrayListInit(contacts);
                //m.addInRightSection();


            }


        }

        if(requestCode == REQ_CODE_CONTACT_PROFILE && resultCode == Activity.RESULT_OK){

        }
    }

    public void deleteButtonClick(View view){

        for(int i = 0; i < contacts.size(); i++){

            if(contacts.get(i).isSelected()){
                ArrayList<Contact> rel = contacts.get(i).getRelationship();
                for(int j = 0; j < rel.size(); j++){
                    System.out.println(rel.get(j).getRelationship().indexOf(contacts.get(i)));
                    rel.get(j).getRelationship().remove(rel.get(j).getRelationship().indexOf(contacts.get(i)));
                }
                contacts.remove(i);
            }
        }
        populateList(contacts);


    }

    public void populateList(ArrayList<Contact> contacts){
        ListAdapter customAdapter = new CustomAdapter(getActivity(), true, contacts);
        //listView = (ListView)view.findViewById(R.id.contactListView);
        listView.setAdapter(customAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("contactArrayList", contacts);
    }
}
