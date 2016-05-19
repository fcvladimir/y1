package com.example.yalantis.y1.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class District extends RealmObject {

    @PrimaryKey
    private long id;

    private String ru_name;

    private String name;

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

}
