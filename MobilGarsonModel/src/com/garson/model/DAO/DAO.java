/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.HibernateUtil;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BurakS
 */
public class DAO<T>
{

    private SessionFactory FACTORY;
    protected Session session = null;

    public DAO()
    {
        FACTORY = HibernateUtil.getSessionFactory();
    }

    /**
     *
     * @param c must be POJO/Entity class object It cannot be like
     * Integer,String etc..
     */
    protected T add(T t)
    {

        try
        {

            session = FACTORY.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.save(t);

            transaction.commit();

            System.out.println("Saved");
            return t;

        } catch (HibernateException ex)
        {
            System.out.println(ex.getMessage());
            return t;
        }

    }

    protected T update(T t)
    {
        try
        {

            session = FACTORY.openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.update(t);

            transaction.commit();

            System.out.println("Updated");
            return t;

        } catch (HibernateException ex)
        {
            System.out.println(ex.getMessage());
            return t;
        }

    }

    protected boolean delete(Class c, long id)
    {

        try
        {

            session = FACTORY.openSession();
            session.beginTransaction();

            Object x = (Object) session.get(c, id);

            session.delete(x);
            session.getTransaction().commit();

            System.out.println("Deleted");
            return true;

        } catch (HibernateException ex)
        {
            System.out.println("com.garson.model.DAO.DAO.delete()" + ex.getCause());
            return false;
        }

    }

    protected T findByID(Class c, long id)
    {
        try
        {
            session = FACTORY.openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(c).add(Restrictions.eq("id", id));
            T object = (T) criteria.uniqueResult();
            session.getTransaction().commit();
           
            return object;
        } catch (HibernateException e)
        {
            System.err.println(e.getClass() + " " + e.getMessage());
            return null;
        }
    }

    protected List<T> getAllRecords(Class c)
    {

        try
        {

            session = FACTORY.openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(c);
            List<T> list = criteria.list();

            session.getTransaction().commit();
            return list;

        } catch (HibernateException e)
        {

            System.out.println(e.getMessage());
            return null;

        }
    }

    /**
     * Restrictions usage
     *
     * @param c
     * @param crit
     * @return
     */
    protected List<T> getListByCriteria(Class c, Criterion crit)
    {

        try
        {

            session = FACTORY.openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(c);
            criteria.add(crit);

            List<T> list = criteria.list();
            session.getTransaction().commit();
            return list;

        } catch (HibernateException e)
        {

            System.out.println(e.getMessage());
            return null;

        }
    }

    protected List<T> getListWithLogicalExp(Class c, LogicalExpression exp)
    {
        try
        {

            session = FACTORY.openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(c);
            criteria.add(exp);

            List<T> list = criteria.list();
            session.getTransaction().commit();
            return list;

        } catch (HibernateException e)
        {

            System.out.println(e.getMessage());
            return null;

        }
    }

    protected int getCountRecords(Class c)
    {
        try
        {

            session = FACTORY.openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(c);
            criteria.setProjection(Projections.rowCount());

            List list = criteria.list();
            session.getTransaction().commit();

            return (int) list.get(0);

        } catch (HibernateException e)
        {

            System.out.println(e.getMessage());
            return -1;

        }
    }

    protected Session getOpenedSession()
    {

        if (FACTORY == null)
        {
            FACTORY = HibernateUtil.getSessionFactory();
        }

        if (session == null)
        {
            session = FACTORY.openSession();
        }
        else if (!session.isConnected())
        {
            session = FACTORY.openSession();
        }
        return session;
    }

    public static String getCurrentDate()
    {

        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String timeStr = time.format(formatter);

        return timeStr;
    }

    public static String getCurrentClock()
    {

        Calendar now = Calendar.getInstance();

        if (Calendar.MINUTE < 10)
        {
            return now.get(Calendar.HOUR_OF_DAY) + ":0" + now.get(Calendar.MINUTE);
        }

        return now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
    }

    public static final <T> List<T> getListFromJSON(final Class<T[]> clazz, final String json)
    {
        final T[] jsonToObject = new Gson().fromJson(json, clazz);
        return Arrays.asList(jsonToObject);
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize()
    {

        try
        {
            if (session != null)
            {
                if (session.isConnected())
                {
                    session.close();
                }
            }
            super.finalize();
        } catch (Throwable ex)
        {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
