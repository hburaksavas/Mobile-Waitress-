package com.mobilgarson.homebeans.service;

import com.garson.model.DAO.OrderDAO;
import com.garson.model.entity.Order;
import java.util.List;

public class OrderService
{

    private final OrderDAO orderDAO;

    public OrderService()
    {
        orderDAO = new OrderDAO();
    }

    public Order getOrder_ByOrderId(long id)
    {
        return orderDAO.getByID(id);
    }

    public String updateOrderStatuForReady(long id)
    {
        try
        {
            Order order = orderDAO.getByID(id);
            order.setStatu("Hazır");
            orderDAO.updateOrder(order);
            return "Sipariş hazır";

        } catch (Exception ex)
        {
            return ex.getLocalizedMessage();
        }

    }

    public void updateOrderStatuForPaid(long id)
    {
        try
        {
            Order order = orderDAO.getByID(id);
            order.setStatu("Ödendi");
            orderDAO.updateOrder(order);

        } catch (Exception ex)
        {
        }
    }

    public List<Order> getOrders_isNotReady(long restaurantid)
    {

        return orderDAO.getRestaurantOrdersByStatu(restaurantid, "Beklemede");

    }

    public List<Order> getOrders_ByTableId(long restaurantid, long id)
    {
        return orderDAO.getRestaurantOrderByDinnerTableid(restaurantid, id);
    }

    public String orderUpdatePaid(long orderid, int paidCount)
    {
        Order order = orderDAO.getByID(orderid);

        if (order.getStatu().equals("Ödendi"))
        {
            return "Ödenen sipariş";
        }

        if (order.getPiece() == paidCount)
        {
            order.setStatu("Ödendi");
            orderDAO.updateOrder(order);
            return "Hepsi ödendi";

        }
        else if (order.getPiece() > paidCount)
        {

            order.setPiece(order.getPiece() - paidCount);
            orderDAO.updateOrder(order);

            Order orderNew = new Order().setClock(order.getClock())
                    .setDate(order.getDate())
                    .setMasaid(order.getMasaid())
                    .setPiece(paidCount)
                    .setRestaurantid(order.getRestaurantid())
                    .setStatu("Ödendi")
                    .setUrunid(order.getUrunid());

            orderDAO.addOrder(orderNew);
            return paidCount + " adet ödendi." + (order.getPiece() - paidCount) + " adet kaldı";
        }
        else
        {
            return "Ödeme gerçekleştirilemedi";
        }

    }

}
