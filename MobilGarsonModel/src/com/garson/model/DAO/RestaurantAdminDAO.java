package com.garson.model.DAO;

import com.garson.model.entity.RestaurantAdmin;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class RestaurantAdminDAO extends DAO<RestaurantAdmin>
{

    public RestaurantAdminDAO()
    {
        super();
    }

    public RestaurantAdmin addAdmin(RestaurantAdmin admin)
    {
        if (admin != null)
            return add(admin);
        else
            return null;
    }

    public RestaurantAdmin updateAdmin(RestaurantAdmin admin)
    {
        if (admin != null)
        {
            return update(admin);
        }
        else
        {
            return null;
        }

    }

    public boolean deleteAdmin(int id)
    {
        if (id < 0)
            return false;
        else
            return delete(RestaurantAdmin.class, id);
    }

    public List<RestaurantAdmin> getAdminListByRestaurantid(long id)
    {
        return getListByCriteria(RestaurantAdmin.class, Restrictions.eq("restaurantid", id));
    }

    public RestaurantAdmin login(long restid, String mail, String password)
    {
        String hql = "from RestaurantAdmin u where u.mail =:mail and u.password=:pass and u.restaurantid=:rid";

        session = getOpenedSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("mail", mail);
        query.setParameter("pass", password);
        query.setParameter("rid", restid);
        
        List<RestaurantAdmin> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0)
        {
            return list.get(0);
        }

        return null;
    }

}
