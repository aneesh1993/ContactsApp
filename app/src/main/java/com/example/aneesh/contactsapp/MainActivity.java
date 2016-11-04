package com.example.aneesh.contactsapp;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PortraitMain.LeftSectionListener, PortraitMain.AddButtonLandListener{

    public ArrayList<Contact> c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            /*
            PortraitContactProfile frag = new PortraitContactProfile();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag).commit();
            */
            //Fragment frag = (Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

            if(getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null){
                PortraitContactProfile frag = (PortraitContactProfile)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                frag = new PortraitContactProfile();
                frag.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag).commit();
            }
            else{
                if(getSupportFragmentManager().findFragmentById(R.id.fragment_container).toString().startsWith("PortraitContactProfile")){
                    PortraitContactProfile frag = (PortraitContactProfile)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if(frag == null){
                        System.out.println("here");
                        frag = new PortraitContactProfile();
                        frag.setArguments(getIntent().getExtras());
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag).commit();
                    }
                }else{
                    PortraitContactDetails frag = (PortraitContactDetails) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if(frag == null){
                        System.out.println("here");
                        frag = new PortraitContactDetails();
                        frag.setArguments(getIntent().getExtras());
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag).commit();
                    }
                }
            }


        }

    }

    public void arrayListInit(ArrayList<Contact> contacts){
        c = contacts;

    }

    public void detailsFragDataPass(ArrayList<Contact> contacts){
        PortraitMain pm = (PortraitMain)getSupportFragmentManager().findFragmentById(R.id.fragment);
        pm.populateList(contacts);

        PortraitContactProfile newFragment = new PortraitContactProfile();
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void changeRightSection(Contact contact) {

        if(!getSupportFragmentManager().findFragmentById(R.id.fragment_container).toString().startsWith("PortraitContactProfile")){
            PortraitContactProfile newFragment = new PortraitContactProfile();
            Bundle args = new Bundle();
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        PortraitContactProfile prof = (PortraitContactProfile)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        prof.landscapeModifications(contact);

    }

    @Override
    public void addInRightSection() {
        if(!getSupportFragmentManager().findFragmentById(R.id.fragment_container).toString().startsWith("PortraitContactDetails")){
            PortraitContactDetails newFragment = new PortraitContactDetails();
            Bundle args = new Bundle();
            args.putSerializable("contacts", c);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }


    }
}
