package com.mobilgarson.services;

import com.garson.model.DAO.DinnerTableDAO;
import com.garson.model.entity.DinnerTable;
import com.mobilgarson.client.GarsonWebClient;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("tableservice")
public class DinnerTableService extends DinnerTableDAO
{

    @Context
    private UriInfo context;

    public DinnerTableService()
    {
    }

    @GET
    @Path("tables/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public DinnerTable getDinnerTable(@PathParam("id") long id)
    {
        if (id < 1)
            return null;

        return getByID(id);
    }

    @GET
    @Path("tables/restaurant/empty/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<DinnerTable> getRestaurantEmptyTables(@PathParam("id") long id)
    {
        return getRastauranTablesByStatu(id, 0);
    }

    @GET
    @Path("tables/restaurant/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<DinnerTable> getRestaurantAllTables(@PathParam("id") long id)
    {
        return getRestaurantTables(id);
    }

    @POST
    @Path("tables/open")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String openTable(@FormParam("tableid") long id)
    {
        DinnerTable table = getByID(id);

        if (table == null)
            return "This table is not exist " + id;

        if (table.getStatu().equals(1))
        {
            return "This table is opened, before.";
        }
        else
        {
            table.setStatu(1);
            updateDinnerTable(table);

            GarsonWebClient client = new GarsonWebClient();
            String message = "open:table:" + table.getId() + ":" + table.getRestaurantid();
            client.sendMessage(message);

            return "OK!";
        }
    }

    @PUT
    @Path("tables/close")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String closeTable(@FormParam("tableid") long id)
    {
        DinnerTable table = getByID(id);

        if (table == null)
            return "This table is not exist";

        if (table.getStatu().equals(0))
        {
            return "This table is closed, before.";
        }
        else
        {
            table.setStatu(0);
            updateDinnerTable(table);

            GarsonWebClient client = new GarsonWebClient();
            String message = "close:table:" + table.getId() + ":" + table.getRestaurantid();
            client.sendMessage(message);

            return "OK!";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getServ()
    {
        return context.getPath();
    }
}
