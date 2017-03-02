package com.mobilgarson.managedbeans;

import com.garson.model.DAO.ComplaintDAO;
import com.garson.model.entity.Complaint;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "complaintBean")
@SessionScoped
public class ComplaintBean
{

    private List<Complaint> complaintList;
    private ComplaintDAO complaintDAO;

    public ComplaintBean()
    {
    }

    public void initComplaints(AjaxBehaviorEvent e)
    {
        HttpSession session = SessionUtils.getSession();
        long restaurantid = (long) session.getAttribute("restaurantid");

        complaintDAO = new ComplaintDAO();
        complaintList = complaintDAO.getRestaurantComplaints(restaurantid);

    }
    public List<Complaint> getComplaintList()
    {
        return complaintList;
    }

    public void setComplaintList(List<Complaint> complaintList)
    {
        this.complaintList = complaintList;
    }

}
