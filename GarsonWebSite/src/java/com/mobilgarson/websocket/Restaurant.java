
package com.mobilgarson.websocket;

import javax.websocket.Session;

public class Restaurant {

    private long restaurant_no;
    private Session session;

    public Restaurant()
    {
    }

    public Restaurant(long restaurant_no)
    {
        this.restaurant_no = restaurant_no;
    }

    public Restaurant(long restaurant_no, Session session)
    {
        this.restaurant_no = restaurant_no;
        this.session = session;
    }

    public long getRestaurant_no()
    {
        return restaurant_no;
    }

    public void setRestaurant_no(long restaurant_no)
    {
        this.restaurant_no = restaurant_no;
    }

    public Session getSession()
    {
        return session;
    }

    public void setSession(Session session)
    {
        this.session = session;
    }

}
