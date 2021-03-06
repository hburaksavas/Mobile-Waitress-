package com.garson.model.entity;
// Generated Dec 7, 2016 1:03:25 AM by Hibernate Tools 4.3.1

/**
 * Zreport generated by hbm2java
 */
public class Zreport implements java.io.Serializable
{

    private Long id;
    private Long restaurantid;
    private String date;
    private String clock;
    private String bills;
    private Double income;

    public Zreport()
    {
    }

    public Zreport(Long restaurantid, String date, String clock, String bills, Double income)
    {
        this.restaurantid = restaurantid;
        this.date = date;
        this.clock = clock;
        this.bills = bills;
        this.income = income;
    }

    public Long getId()
    {
        return this.id;
    }

    public Zreport setId(Long id)
    {
        this.id = id;
        return this;

    }

    public Long getRestaurantid()
    {
        return this.restaurantid;
    }

    public Zreport setRestaurantid(Long restaurantid)
    {
        this.restaurantid = restaurantid;
        return this;

    }

    public String getDate()
    {
        return this.date;
    }

    public Zreport setDate(String date)
    {
        this.date = date;
        return this;

    }

    public String getClock()
    {
        return this.clock;
    }

    public Zreport setClock(String clock)
    {
        this.clock = clock;
        return this;

    }

    public String getBills()
    {
        return this.bills;
    }

    public Zreport setBills(String bills)
    {
        this.bills = bills;
        return this;

    }

    public Double getIncome()
    {
        return this.income;
    }

    public Zreport setIncome(Double income)
    {
        this.income = income;
        return this;
    }

}
