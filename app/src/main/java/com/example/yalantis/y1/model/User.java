package com.example.yalantis.y1.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private long id;

    private String birthday;

    private String first_name;

    private String phone;

    private Address address;

    private String email;

    private String middle_name;

    private String last_name;

    private String fb_registered;

    public long getId ()
    {
        return id;
    }

    public void setId (long id)
    {
        this.id = id;
    }

    public String getBirthday ()
    {
        return birthday;
    }

    public void setBirthday (String birthday)
    {
        this.birthday = birthday;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getMiddle_name ()
    {
        return middle_name;
    }

    public void setMiddle_name (String middle_name)
    {
        this.middle_name = middle_name;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getFb_registered ()
    {
        return fb_registered;
    }

    public void setFb_registered (String fb_registered)
    {
        this.fb_registered = fb_registered;
    }

}
