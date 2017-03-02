/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.RestaurantComments;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BurakS
 */
public class RestaurantCommentsDAO extends DAO<RestaurantComments> {

    public RestaurantCommentsDAO() {
        super();
    }

    public RestaurantComments addComment(RestaurantComments comment) {
        return add(comment);
    }

    public RestaurantComments updateComment(RestaurantComments comment) {
        return update(comment);
    }

    public boolean deleteComment(long id) {
        return delete(RestaurantComments.class, id);
    }

    public List<RestaurantComments> getRestaurantComments(long restaurantid) {
        return getListByCriteria(RestaurantComments.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public RestaurantComments getComment(long id){
        return findByID(RestaurantComments.class, id);
    }
}
