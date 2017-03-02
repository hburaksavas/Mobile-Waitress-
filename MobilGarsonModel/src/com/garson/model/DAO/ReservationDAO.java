/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Reservation;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BurakS
 */
public class ReservationDAO extends DAO<Reservation>
{

    public ReservationDAO()
    {
        super();
    }

    public Reservation addReservation(Reservation res)
    {
        return add(res);
    }

    public Reservation updateReservation(Reservation res)
    {
        return update(res);
    }

    public boolean deleteReservation(long id)
    {
        return delete(Reservation.class, id);
    }

    public boolean deleteAllReservations(long restaurantid)
    {
        String hql = "DELETE FROM Reservation WHERE restaurantid=:restid";
        session = getOpenedSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("restid", restaurantid);

        int up = query.executeUpdate();
        session.getTransaction().commit();
        return up > 0;
    }

    public List<Reservation> getRestaurantReservations(long restaurantid)
    {
        return getListByCriteria(Reservation.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public List<Reservation> getReservationByUser(long userid)
    {
        return getListByCriteria(Reservation.class, Restrictions.eq("userid", userid));
    }

    public Reservation getReservationByDateClock(String date, String clock, long restid, long tableid)
    {
        String hql = "FROM Reservation WHERE restaurantid=:restid AND date=:date AND clock=:clock AND dinnertableid=:tableid";

        session = getOpenedSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("restid", restid);
        query.setParameter("date", date);
        query.setParameter("clock", clock);
        query.setParameter("tableid", tableid);

        List<Reservation> list = query.list();

        if (list == null)
            return null;

        return list.get(0);
    }

    public Reservation getById(long id)
    {
        return findByID(Reservation.class, id);
    }
}
