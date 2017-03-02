package com.mobilgarson.services;

import com.garson.model.DAO.ProductImageDAO;
import com.garson.model.DAO.RestaurantImageDAO;
import com.garson.model.entity.ProductImages;
import com.garson.model.entity.RestaurantImage;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * Resimler base64 string olarak gönderilmektedir, base64 string decode edilerek byte dizisi elde edilerek
 * bu byte dizisinden resim elde edilmelidir.
 */
@Path("imageservice")
public class ImageService
{

    @Context
    private UriInfo context;

    public ImageService()
    {
    }

    /**
     * 
     * @param restaurantid
     * @return Restorana ait resimler
     */
    @GET
    @Path("restaurant/images/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestaurantImage> getRestaurantImageses(@PathParam("id") long restaurantid)
    {
        RestaurantImageDAO dao = new RestaurantImageDAO();
        return dao.getListRestaurantImages(restaurantid);
    }

    /**
     * 
     * @param productid 
     * @return Ürüne ait birden fazla resim var ise, bunu liste olarak gönderir
     */
    @GET
    @Path("product/images/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductImages> getProductImages(@PathParam("id")long productid){        
        return new ProductImageDAO().getProductsImagesListById(productid);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getService(){
        return "Image Service";
    }
}
