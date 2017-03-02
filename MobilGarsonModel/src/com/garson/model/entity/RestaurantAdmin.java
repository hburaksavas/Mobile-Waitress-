package com.garson.model.entity;

public class RestaurantAdmin implements java.io.Serializable
{

    private int id;
    private long restaurantid;
    private String mail;
    private String password;

    public RestaurantAdmin()
    {
    }

    public RestaurantAdmin(int id, long restaurantid, String mail, String password)
    {
        this.id = id;
        this.restaurantid = restaurantid;
        this.mail = mail;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public RestaurantAdmin setId(int id)
    {
        this.id = id;
        return this;

    }

    public long getRestaurantid()
    {
        return restaurantid;
    }

    public RestaurantAdmin setRestaurantid(long restaurantid)
    {
        this.restaurantid = restaurantid;
        return this;

    }

    public String getMail()
    {
        return mail;
    }

    public RestaurantAdmin setMail(String mail)
    {
        this.mail = mail;
        return this;

    }

    public String getPassword()
    {
        return password;
    }

    public RestaurantAdmin setPassword(String password)
    {
        this.password = password;
        return this;
    }

}
