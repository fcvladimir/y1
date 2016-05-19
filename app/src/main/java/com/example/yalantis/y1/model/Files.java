package com.example.yalantis.y1.model;

import io.realm.RealmObject;

public class Files extends RealmObject {

    private String id;

    private String name;

    private String filename;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getFilename ()
    {
        return filename;
    }

    public void setFilename (String filename)
    {
        this.filename = filename;
    }

}
