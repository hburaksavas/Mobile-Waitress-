/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Employee;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author BurakS
 */
public class EmployeeDAO extends DAO<Employee> {

    public EmployeeDAO() {
        super();
    }

    public Employee addEmployee(Employee emp) {
        return add(emp);
    }

    public Employee updateEmployee(Employee emp) {
        return update(emp);
    }

    public boolean deleteEmployee(long id) {
        return delete(Employee.class, id);
    }

    public List<Employee> getRestaurantEmployees(long restaurantID) {
        return getListByCriteria(Employee.class, Restrictions.eq("restaurantid", restaurantID));
    }

    public Employee login(long restaurantid, String mail, String password) {

        String hql = "from Employee u where u.mail =:mail and u.password=:pass and u.restaurantid=:id";
        
        session = getOpenedSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("mail", mail);
        query.setParameter("pass", password);
        query.setParameter("id", restaurantid);

        List<Employee> list = query.list();

        session.getTransaction().commit();
        session.close();

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    public Employee getByID(long id) {
        return findByID(Employee.class, id);
    }

    public Employee updateScore(long id, int score) {

        try {
            Employee rest = getByID(id);

            int voteCount;
            double newScore;

            if (rest.getVoteCount() != null) {
                voteCount = rest.getVoteCount();
                newScore = ((rest.getScore() * voteCount) + score) / (++voteCount);
            } else {
                voteCount = 1;
                newScore = score;
            }

            rest = rest.setScore(newScore).setVoteCount(voteCount);
            return updateEmployee(rest);

        } catch (Exception ex) {
            System.out.println(ex.getCause());
            return null;
        }

    }
    
}
