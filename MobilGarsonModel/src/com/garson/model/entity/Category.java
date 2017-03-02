package com.garson.model.entity;
// Generated Dec 7, 2016 1:03:25 AM by Hibernate Tools 4.3.1

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable
{

    private Long id;
    private long restaurantid;
    private String name;

    public Category()
    {
    }

    public Category(long restaurantid, String name)
    {
        this.restaurantid = restaurantid;
        this.name = name;
    }

    public Long getId()
    {
        return this.id;
    }

    public Category setId(Long id)
    {
        this.id = id;
        return this;

    }

    public long getRestaurantid()
    {
        return this.restaurantid;
    }

    public Category setRestaurantid(long restaurantid)
    {
        this.restaurantid = restaurantid;
        return this;

    }

    public String getName()
    {
        return this.name;
    }

    public Category setName(String name)
    {
        this.name = name;
        return this;
    }

}
