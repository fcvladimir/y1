package com.example.yalantis.y1.model.task;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Street extends RealmObject {

    @PrimaryKey
    private long id;

    private String ru_name;

    private String name;

    private Street_type street_type;

    public long getId ()
    {
        return id;
    }

    public void setId (long id)
    {
        this.id = id;
    }

    public String getRu_name ()
    {
        return ru_name;
    }

    public void setRu_name (String ru_name)
    {
        this.ru_name = ru_name;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Street_type getStreet_type ()
    {
        return street_type;
    }

    public void setStreet_type (Street_type street_type)
    {
        this.street_type = street_type;
    }

}
