package com.example.aneesh.contactsapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Aneesh on 10/26/2016.
 */

public class Contact implements Serializable {

    String name = null;
    boolean selected = false;
    boolean relationBool = false;
    String contactNumber = null;
    ArrayList<Contact> relationship = new ArrayList<>();

    public Contact(String name, String contactNumber){
        super();
        this.name = name;
        this.contactNumber = contactNumber;
        this.selected = false;
        this.relationBool = false;
    }


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getContactNumber(){
        return contactNumber;
    }
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }


    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public boolean isRelationBool(){
        return relationBool;
    }
    public void setRelationBool(boolean relBool){
        this.relationBool = relBool;
    }

    public void addRelationship(Contact rel){
        this.relationship.add(rel);
    }
    public ArrayList<Contact> getRelationship(){
        return relationship;
    }
}
