/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.User;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author BurakS
 */
public class UserDAO extends DAO<User> {

    public UserDAO() {
        super();
    }

    public User addUser(User user) {
        return add(user);
    }

    public User updateUser(User user) {
        return update(user);
    }

    public boolean deleteUser(int id) {
        return delete(User.class, id);
    }

    public List<User> getUsers() {
        return getAllRecords(User.class);
    }

    public User login(String mail, String password) {
        
        String hql = "from User u where u.mail =:mail and u.password=:pass";
        
        session = getOpenedSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("mail", mail);
        query.setParameter("pass", password);

        List<User> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }
    
    
}
