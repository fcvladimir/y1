package com.example.yalantis.y1.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pinta on 18.05.2016.
 */
public class Address extends RealmObject {

    @PrimaryKey
    private long id;

    private String flat;

    private Street street;

    private House house;

    private District district;

    private City city;

    public long getId ()
    {
        return id;
    }

    public void setId (long id)
    {
        this.id = id;
    }

    public String getFlat ()
    {
        return flat;
    }

    public void setFlat (String flat)
    {
        this.flat = flat;
    }

    public Street getStreet ()
    {
        return street;
    }

    public void setStreet (Street street)
    {
        this.street = street;
    }

    public House getHouse ()
    {
        return house;
    }

    public void setHouse (House house)
    {
        this.house = house;
    }

    public District getDistrict ()
    {
        return district;
    }

    public void setDistrict (District district)
    {
        this.district = district;
    }

    public City getCity ()
    {
        return city;
    }

    public void setCity (City city)
    {
        this.city = city;
    }

}
