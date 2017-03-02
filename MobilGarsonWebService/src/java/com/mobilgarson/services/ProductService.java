package com.mobilgarson.services;

import com.garson.model.DAO.ProductDAO;
import com.garson.model.entity.Product;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("productservice")
public class ProductService extends ProductDAO
{

    @Context
    private UriInfo context;

    public ProductService()
    {

    }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Product getProduct(@PathParam("id") long id)
    {
        if (id < 1)
            return new Product();
        else
        {
            return getByID(id);
        }
    }

    @GET
    @Path("/products/restaurant/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Product> getRestaurantProductList(@PathParam("id") long id)
    {
        if (id < 1)
            return null;

        else
        {
            return getRestaurantProducts(id);
        }
    }
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String putJson()
    {
        return "Product Service";
    }
}
