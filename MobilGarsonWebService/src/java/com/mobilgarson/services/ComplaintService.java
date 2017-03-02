package com.mobilgarson.services;

import com.garson.model.DAO.ComplaintDAO;
import com.garson.model.entity.Complaint;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("complaintservice")
public class ComplaintService extends ComplaintDAO
{

    @Context
    private UriInfo context;

    public ComplaintService()
    {
    }

    @GET
    @Path("complaints/restaurant/{id}")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public List<Complaint> getRestaurantComplaintList(@PathParam("id") long id)
    {
        return getRestaurantComplaints(id);
    }

    @GET
    @Path("complaints/user/{id}")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public List<Complaint> getUsersComplaintList(@PathParam("id") long id)
    {
        if (id < 1)
            return null;
        return getUserComplaints(id);
    }

    @PUT
    @Path("complaints")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Complaint createComplaint(
            @FormParam("complaintkonusu") String complaintHeader,
            @FormParam("complainticerigi") String complaintBody,
            @FormParam("restaurantid") long resID,
            @FormParam("userid") long userID,
            @Context HttpServletResponse servletResponse)
    {

        Complaint complaint = new Complaint()
                .setComplaintHeader(complaintHeader)
                .setComplaintBody(complaintBody)
                .setRestaurantid(resID)
                .setUserid(userID);

        return addComplaint(complaint);
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public String putJson()
    {
        return "Complaint Service";
    }
}
