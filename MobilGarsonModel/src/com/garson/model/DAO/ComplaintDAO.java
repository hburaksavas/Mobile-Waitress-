package com.garson.model.DAO;

import com.garson.model.entity.Complaint;
import java.util.List;
import org.hibernate.criterion.Restrictions;

public class ComplaintDAO extends DAO<Complaint> {

    public ComplaintDAO() {
        super();
    }

    public Complaint addComplaint(Complaint complaint) {
        return add(complaint);
    }

    public Complaint updateComplaint(Complaint complaint) {
        return update(complaint);
    }

    public boolean deleteComplaint(long id) {
        return delete(Complaint.class, id);
    }

    public List<Complaint> getRestaurantComplaints(long restaurantid) {
        return getListByCriteria(Complaint.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public List<Complaint> getUserComplaints(long userid) {
        return getListByCriteria(Complaint.class, Restrictions.eq("userid", userid));
    }
    
    public Complaint getByID(long id){
        return findByID(Complaint.class,id);
    }
}
