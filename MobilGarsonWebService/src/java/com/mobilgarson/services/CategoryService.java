package com.mobilgarson.services;

import com.garson.model.DAO.CategoryDAO;
import com.garson.model.entity.Category;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("categoryservice")
public class CategoryService extends CategoryDAO
{

    @Context
    private UriInfo context;

    public CategoryService()
    {
    }

    @GET
    @Produces("application/json")
    public String getService()
    {
        return "Category Service";
    }

    @GET
    @Path("category/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Category getCategory(@PathParam("id") long id)
    {
        Category category = new Category().setId(-1l).setName("Böyle bir kategori yoktur").setRestaurantid(-1);

        if (id < 1)
        {
            return category;
        }
        else
        {
            return getByID(id);
        }


    }

    @GET
    @Path("category/restaurant/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Category> getRestaurantCategories(@PathParam("id") long id)
    {
        if (id < 1)
            return null;
        return getAllCategories(id);
    }

    
}
