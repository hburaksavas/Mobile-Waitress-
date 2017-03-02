package com.mobilgarson.services;

import com.garson.model.DAO.RestaurantDAO;
import com.garson.model.entity.Restaurant;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("restaurantservice")
public class RestaurantService extends RestaurantDAO
{

    @Context
    private UriInfo context;

    public RestaurantService()
    {
    }

    @GET
    @Path("restaurants")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public List<Restaurant> getAllRestaurant()
    {
        return getRestaurants();
    }

    @GET
    @Path("restaurants/{id}")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public Restaurant getRestaurant(@PathParam("id") long id)
    {
        if (id < 1)
            return null;

        return getByID(id);
    }
//
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public String putJson()
    {
        return "Restaurant Service, \n" +
        context.getRequestUri()+
                "\nGet All Restaurants Path -> restaurants\n"+
                "Get Restaurant By id Path -> restaurants/{id}";
    }
    

}
