/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Order;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BurakS
 */
public class OrderDAO extends DAO<Order> {

    public OrderDAO() {
        super();
    }

    public Order addOrder(Order order) {
        return add(order);
    }

    public Order updateOrder(Order order) {
        return update(order);
    }

    public boolean deleteOrder(long orderid) {
        return delete(Order.class, orderid);
    }

    public boolean deleteOrdersByTableId(long tableid) {
        String hql = "DELETE FROM Order WHERE masaid = :masaid";
        session = getOpenedSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("masaid", tableid);

        int up = query.executeUpdate();
        session.getTransaction().commit();
        return up > 0;
    }

    public boolean deleteRestaurantOrders(long restaurantid) {
        String hql = "DELETE FROM Order WHERE restaurantid = :resid";
        session = getOpenedSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("resid", restaurantid);

        int up = query.executeUpdate();
        session.getTransaction().commit();
        return up > 0;
    }

    public List<Order> getRestaurantOrders(long restaurantid) {
        return getListByCriteria(Order.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public List<Order> getRestaurantOrderByDinnerTableid(long restaurantid, long tableid) {
        String hql = "from Order u where u.restaurantid =:res and u.masaid=:tbl";

        session = getOpenedSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("res", restaurantid);
        query.setParameter("tbl", tableid);

        List<Order> list = query.list();

        session.getTransaction().commit();
        session.close();

        return list;
    }

    public List<Order> getRestaurantOrdersByStatu(long restaurantid, String statu) {
        String hql = "from Order u where u.restaurantid =:res and u.statu=:stt";

        session = getOpenedSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("res", restaurantid);
        query.setParameter("stt", statu);

        List<Order> list = query.list();

        session.getTransaction().commit();
        session.close();

        return list;
    }

    public Order getByID(long id) {
        return findByID(Order.class, id);
    }

}
