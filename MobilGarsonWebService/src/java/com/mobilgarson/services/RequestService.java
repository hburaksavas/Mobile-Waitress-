package com.mobilgarson.services;

import com.garson.model.DAO.RequestDAO;
import com.garson.model.entity.Request;
import com.mobilgarson.client.GarsonWebClient;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.hibernate.criterion.Restrictions;

@Path("requestservice")
public class RequestService extends RequestDAO
{

    @Context
    private UriInfo context;

    public RequestService()
    {
    }

    /**
     * Request id'ye göre request bilgilerini getirir
     *
     * @param id
     * @return
     */
    @GET
    @Path("request/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public com.garson.model.entity.Request getRequestById(@PathParam("id") long id)
    {
        return getByID(id);
    }

    /**
     * User id'ye göre istekleri listeler
     *
     * @param id
     * @return
     */
    @GET
    @Path("requests/user/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Request> getAllRequestsByKullaniciID(@PathParam("id") long id)
    {
        return getListByCriteria(Request.class, Restrictions.eq("userid", id));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Request createRequest(@FormParam("tableid") long tableid,
            @FormParam("userid") long userid,
            @FormParam("restaurantid") long restid,
            @FormParam("garsonid") long garsonid)
    {
        Request request = new Request()
                .setDinnertableid(tableid)
                .setGarsonid(garsonid)
                .setRestaurantid(restid)
                .setUserid(userid);

        addRequest(request);

        GarsonWebClient client = new GarsonWebClient();
        String message = "request:waitress:" + tableid + ":" + garsonid + ":" + restid;
        client.sendMessage(message);

        if (request.getId() > 0)
            return request;
        else
            return null;

    }

    @DELETE
    @Path("request/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public boolean cancelRequest(@PathParam("id") long id)
    {
        return deleteRequest(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations()
    {
        return "<operations>GET, PUT, DELETE</operations>";
    }
}
