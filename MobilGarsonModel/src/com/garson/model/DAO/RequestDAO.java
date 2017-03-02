/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Request;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BurakS
 */
public class RequestDAO extends DAO<Request> {

    public RequestDAO() {
        super();
    }

    public Request addRequest(Request r) {
        return add(r);
    }

    public Request updateRequest(Request r) {
        return update(r);
    }

    public boolean deleteRequest(long id) {
        return delete(Request.class, id);
    }

    public boolean deleteRestaurantRequestsAll(long restaurantid) {
        String hql = "DELETE FROM Request WHERE restaurantid = :restid";
        session = getOpenedSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("restid", restaurantid);

        int up = query.executeUpdate();
        session.getTransaction().commit();
        return up > 0;
    }

    public List<Request> getRestaurantRequestList(long restaurantid) {
        return getListByCriteria(Request.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public Request getByID(long id) {
        return findByID(Request.class, id);
    }
    
}
