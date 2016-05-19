package com.example.yalantis.y1.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Geo_address extends RealmObject {

    @PrimaryKey
    private long id;

    private String address;

    private String longitude;

    private String latitude;

    public long getId ()
    {
        return id;
    }

    public void setId (long id)
    {
        this.id = id;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

}
