package com.mobilgarson.services;

import com.garson.model.DAO.DAO;
import com.garson.model.DAO.OrderDAO;
import com.garson.model.entity.Order;
import com.mobilgarson.client.GarsonWebClient;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("orderservice")
public class OrderService extends OrderDAO
{

    @Context
    private UriInfo context;

    public OrderService()
    {
    }

    @PUT
    @Path("order/give")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Order giveOrder(@FormParam("restaurantid") long restid,
            @FormParam("productid") long productid,
            @FormParam("piece") int piece,
            @FormParam("tableid") long masaid)
    {
        Order order = new Order().setClock(DAO.getCurrentClock())
                .setDate(DAO.getCurrentDate())
                .setMasaid(masaid)
                .setPiece(piece)
                .setRestaurantid(restid)
                .setStatu("Beklemede")
                .setUrunid(productid);
        order = addOrder(order);

        GarsonWebClient client = new GarsonWebClient();
        String message = "new:order:" + order.getRestaurantid();
        client.sendMessage(message);

        return order;
    }

    @DELETE
    @Path("order/cancel/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String cancelOrder(@PathParam("id") long id)
    {
        Order order = getByID(id);
        if (order.getStatu().equals("Beklemede"))
        {
            deleteOrder(id);

            GarsonWebClient client = new GarsonWebClient();
            String message = "new:order:" + order.getRestaurantid();
            client.sendMessage(message);
        }
        else
        {
            return "Sipariş İptali Gerçekleştirilemiyor";
        }
        return "OK!";
    }

    @POST
    @Path("order/table")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Order> listOrderByTableId(@FormParam("restaurantid") long restid,
            @FormParam("tableid") long tableid)
    {
        return getRestaurantOrderByDinnerTableid(restid, tableid);
    }

    @POST
    @Path("order/update")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Order updatesOrder(@FormParam("orderid") long id,
            @FormParam("restaurantid") long restid,
            @FormParam("productid") long productid,
            @FormParam("piece") int piece,
            @FormParam("tableid") long masaid)
    {
        Order order = new Order().setClock(DAO.getCurrentClock())
                .setDate(DAO.getCurrentDate())
                .setMasaid(masaid)
                .setPiece(piece)
                .setRestaurantid(restid)
                .setStatu("Beklemede")
                .setUrunid(id)
                .setUrunid(productid);

        GarsonWebClient client = new GarsonWebClient();
        String message = "new:order:" + restid;
        client.sendMessage(message);

        return updateOrder(order);
    }

    @GET
    @Path("order/{orderid}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Order takeOrder(@PathParam("orderid") long id)
    {
        return getByID(id);
    }
}
