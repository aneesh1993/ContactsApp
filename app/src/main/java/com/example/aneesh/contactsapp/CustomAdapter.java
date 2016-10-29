package com.example.aneesh.contactsapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aneesh on 10/26/2016.
 */

class CustomAdapter extends ArrayAdapter<Contact> {      // <Each item inside the arrayList - actual datatype used>

    Context c;
    boolean isDelete;
    public CustomAdapter(Context context, boolean delete, ArrayList<Contact> contacts) {
        super(context, R.layout.custom_row, contacts);
        c = context;
        isDelete = delete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        final Contact singleContact = getItem(position);

        TextView contactText = (TextView)customView.findViewById(R.id.contactName);
        contactText.setText(singleContact.getName());


        CheckBox cb = (CheckBox)customView.findViewById(R.id.checkbox);
        cb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb1 = (CheckBox)v;

                        if(isDelete){
                            System.out.println("checked");
                            getItem(position).setSelected(cb1.isChecked());
                        }
                        else{
                            System.out.println("relBool checked");
                            getItem(position).setRelationBool(cb1.isChecked());
                        }

                    }
                }
        );

        return customView;
    }
}
