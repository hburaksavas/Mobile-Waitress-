package com.garson.model.DAO;

import com.garson.model.entity.RestaurantImage;
import java.util.List;
import org.hibernate.criterion.Restrictions;

public class RestaurantImageDAO extends DAO<RestaurantImage>
{

    public RestaurantImageDAO()
    {
        super();
    }

    public RestaurantImage addRestaurantImages(RestaurantImage image)
    {
        return add(image);
    }

    public RestaurantImage updaRestaurantImages(RestaurantImage image)
    {
        return update(image);
    }

    public boolean deleteRestauranImages(long imageid)
    {
        return delete(RestaurantImage.class, imageid);
    }

    public List<RestaurantImage> getListRestaurantImages(long restaurantid)
    {
        return getListByCriteria(RestaurantImage.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public RestaurantImage getRestaurantImagesById(long id)
    {
        return findByID(RestaurantImage.class, id);
    }
}
