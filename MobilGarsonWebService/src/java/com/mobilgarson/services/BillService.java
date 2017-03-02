package com.mobilgarson.services;

import com.garson.model.DAO.DinnerTableDAO;
import com.garson.model.DAO.OrderDAO;
import com.garson.model.DAO.ProductDAO;
import com.garson.model.entity.Bill;
import com.garson.model.entity.Order;
import com.garson.model.entity.Product;
import com.mobilgarson.client.GarsonWebClient;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("billservice")
public class BillService
{

    @Context
    private UriInfo context;

    public BillService()
    {
    }

    @GET
    @Path("/{tableid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bill getBill(@PathParam("tableid") long id)
    {
        DinnerTableDAO tableDAO = new DinnerTableDAO();
        long restaurantId = tableDAO.getRestaurantIdByDinnerTableId(id);
        OrderDAO dao = new OrderDAO();
        List<Order> orders = dao.getRestaurantOrderByDinnerTableid(restaurantId, id);
        ProductDAO proDAO = new ProductDAO();
        double total = 0d;
        //Toplam hesap tutarını hesaplama
        for (Order o : orders)
        {

            Product product = proDAO.getByID(o.getUrunid());

            total += product.getPrice() * o.getPiece();

        }

        GarsonWebClient client = new GarsonWebClient();
        String message = "request:bill:" + id + ":" + restaurantId;
        client.sendMessage(message);

        return new Bill().setAmount(total).setRestaurantid(restaurantId);

    }

}
