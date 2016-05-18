package com.example.yalantis.y1.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TaskBean extends RealmObject {

    private long created_date;

    private RealmList<Files> files;

    private String body;

    private String ticket_id;

    private State state;

    private Type type;

    @PrimaryKey
    private long id;

    private RealmList<Performer> performers;

    private Category category;

    private String title;

    private Address address;

    private String likes_counter;

    private String deadline;

    private User user;

    private Geo_address geo_address;

    private String start_date;

    public long getCreated_date ()
    {
        return created_date;
    }

    public void setCreated_date (long created_date)
    {
        this.created_date = created_date;
    }

    public RealmList<Files> getFiles ()
    {
        return files;
    }

    public void setFiles (RealmList<Files> files)
    {
        this.files = files;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getTicket_id ()
    {
        return ticket_id;
    }

    public void setTicket_id (String ticket_id)
    {
        this.ticket_id = ticket_id;
    }

    public State getState ()
    {
        return state;
    }

    public void setState (State state)
    {
        this.state = state;
    }

    public Type getType ()
    {
        return type;
    }

    public void setType (Type type)
    {
        this.type = type;
    }

    public long getId ()
    {
        return id;
    }

    public void setId (long id)
    {
        this.id = id;
    }

    public RealmList<Performer> getPerformers ()
    {
        return performers;
    }

    public void setPerformers (RealmList<Performer> performers)
    {
        this.performers = performers;
    }

    public Category getCategory ()
    {
        return category;
    }

    public void setCategory (Category category)
    {
        this.category = category;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }

    public String getLikes_counter ()
    {
        return likes_counter;
    }

    public void setLikes_counter (String likes_counter)
    {
        this.likes_counter = likes_counter;
    }

    public String getDeadline ()
    {
        return deadline;
    }

    public void setDeadline (String deadline)
    {
        this.deadline = deadline;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public Geo_address getGeo_address ()
    {
        return geo_address;
    }

    public void setGeo_address (Geo_address geo_address)
    {
        this.geo_address = geo_address;
    }

    public String getStart_date ()
    {
        return start_date;
    }

    public void setStart_date (String start_date)
    {
        this.start_date = start_date;
    }

}
